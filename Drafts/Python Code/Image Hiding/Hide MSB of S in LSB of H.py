'''
Hide MSB of S in LSB of H.
'''

from PIL import Image

# Open the host and secret images
host_image = Image.open("host_image.png")
secret_image = Image.open("secret_image.png")

# Get the pixel data of the images
host_pixels = host_image.load()
secret_pixels = secret_image.load()

# Hide the MSB of the secret image in the LSB of the host image
for x in range(host_image.size[0]):
    for y in range(host_image.size[1]):
        # Get the RGB values of the host and secret pixels
        host_r, host_g, host_b = host_pixels[x, y]
        secret_r, secret_g, secret_b = secret_pixels[x, y]

        # Hide the MSB of the secret pixel in the LSB of the host pixel
        host_r = (host_r & 254) | ((secret_r >> 7) & 1)
        host_g = (host_g & 254) | ((secret_g >> 7) & 1)
        host_b = (host_b & 254) | ((secret_b >> 7) & 1)

        # Update the pixel data of the host image
        host_pixels[x, y] = (host_r, host_g, host_b)

# Save the modified host image
host_image.save("hidden_image.png")
