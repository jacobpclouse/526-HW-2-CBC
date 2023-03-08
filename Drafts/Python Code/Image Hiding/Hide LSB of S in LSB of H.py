'''
Hide LSB of S in LSB of H.
'''
from PIL import Image

def ImageHiding(H_path, S_path, output_path):
    # Load the host and secret images
    H = Image.open(H_path)
    S = Image.open(S_path)

    # Ensure the images have the same size
    if H.size != S.size:
        raise ValueError("The host and secret images must have the same size.")

    # Get the pixels of the host and secret images
    H_pixels = H.load()
    S_pixels = S.load()

    # Iterate over each pixel of the images and hide the LSB of S in the LSB of H
    for i in range(H.size[0]):
        for j in range(H.size[1]):
            H_r, H_g, H_b = H_pixels[i, j]
            S_r, S_g, S_b = S_pixels[i, j]

            # Hide the LSB of S in the LSB of H
            H_r = (H_r & 254) | (S_r & 1)
            H_g = (H_g & 254) | (S_g & 1)
            H_b = (H_b & 254) | (S_b & 1)

            # Update the pixel in the host image
            H_pixels[i, j] = (H_r, H_g, H_b)

    # Save the resulting image to the output path
    H.save(output_path)
