from google.cloud import storage
import tensorflow as tf
from PIL import Image
import numpy as np
from flask import jsonify

model = None
BUCKET_NAME = "potatoplant-tf-model" 
class_names = ["Early Blight", "Late Blight", "Healthy"]


def download_blob(bucket_name, source_blob_name, destination_file_name):
    """Downloads a blob from the bucket."""
    storage_client = storage.Client()
    bucket = storage_client.bucket(bucket_name)
    
    blob = bucket.blob(source_blob_name)
    blob.download_to_filename(destination_file_name)
    print(f"Blob {source_blob_name} downloaded to {destination_file_name}.")
    

def predict(request):
    global model
    
    if model is None:
        try:
            download_blob(
                BUCKET_NAME,
                "models/potatoes.h5",
                "/tmp/potatoes.h5",
            )
            model = tf.keras.models.load_model("/tmp/potatoes.h5")
            
        except Exception as e:
            return {"error": "No file part in the request"}, 400

    image = request.files["file"]

    try:
        image = np.array(
            Image.open(image).convert("RGB").resize((256, 256)) # image resizing
        )
    except Exception as e:
        return {"error": f"Invalid image file: {str(e)}"}, 400

    image = np.array(image) / 255.0 # normalize the image in 0 to 1 range
    img_array = tf.expand_dims(image, 0)
    
    try:
        predictions = model.predict(img_array)
        predicted_class = class_names[np.argmax(predictions[0])]
        confidence = round(100 * (np.max(predictions[0])), 2)

    except Exception as e:
        return {"error": f"Failed to make predictions: {str(e)}"}, 500
    
    return jsonify({"class": predicted_class, "confidence": confidence})
