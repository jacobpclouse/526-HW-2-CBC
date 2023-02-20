# Jacob Clouse, 2023 - This Python Program was written on Windows 10 and Linux Mint using VScode, your milage may vary based on your OS and configuration.

'''
This program will impliment:
 ______    _______   ______      
/_____/\ /_______/\ /_____/\     
\:::__\/ \::: _  \ \\:::__\/     
 \:\ \  __\::(_)  \/_\:\ \  __   
  \:\ \/_/\\::  _  \ \\:\ \/_/\  
   \:\_\ \ \\::(_)  \ \\:\_\ \ \ 
    \_____\/ \_______\/ \_____\/ 
                                 
(Cipher Block Chaining)
'''

# GOALS for Development:
# 1st: figure out how to encrypt with AES (just one step) - ie: how to XOR and what you get as output
# 2nd: figure out how to take that output, store it, then use it again in the same encryption
# 3rd: make sure that you can still decrypt this
# 4th: figure out how to run it N times and still decrypt it
# 5th: 
# 6th: 

# =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
# Importing Libraries / Modules 
# =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

import datetime # used to get the datetime for "defang_datetime" function
#import random # one time pad use

# =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
# Variables
# =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-


# =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
# Functions
# =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

# --- Function to print out my Logo ---
def myLogo():
    print("Created and Tested by: ")
    print("   __                  _         ___ _                       ")
    print("   \ \  __ _  ___ ___ | |__     / __\ | ___  _   _ ___  ___  ")
    print("    \ \/ _` |/ __/ _ \| '_ \   / /  | |/ _ \| | | / __|/ _ \ ")
    print(" /\_/ / (_| | (_| (_) | |_) | / /___| | (_) | |_| \__ \  __/ ")
    print(" \___/ \__,_|\___\___/|_.__/  \____/|_|\___/ \__,_|___/\___| ")
    print("Dedicated to Peter Zlomek and Harley Alderson III")


# --- Function to Defang date time ---
def defang_datetime():
    current_datetime = f"_{datetime.datetime.now()}"

    current_datetime = current_datetime.replace(":","_")
    current_datetime = current_datetime.replace(".","-")
    current_datetime = current_datetime.replace(" ","_")
    
    return current_datetime

# --- Function to break down data into 128 bit blocks - REFACTOR ---
def break_it_down(input_data):
    block_size = 16  # 16 bytes = 128 bits
    num_blocks = (len(input_data) + block_size - 1) // block_size  # calculate number of blocks
    blocks = []
    for i in range(num_blocks):
        block_start = i * block_size
        block_end = block_start + block_size
        block = input_data[block_start:block_end]
        if len(block) < block_size:
            # pad the last block with zeroes if necessary
            block += bytes(block_size - len(block))
        blocks.append(int.from_bytes(block, 'big'))

    print(f"Number of Blocks: {blocks}")
    return blocks



# --- Function to Encrypt CBC ---
def encrypt_cbc(datetime,plaintext,key,iv):
    # Initialize Encryption Function
    print(f"Starting CBC Encryption at: {datetime}")

    # Creating Array to Store Ciphertext
    encryption_ciphertext = []





# =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
# MAIN PROGRAM
# =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-


# Grabbing DateTime
grab_time = defang_datetime()

# need to have the b before the string 
testVar = b'break it down boi'
break_it_down(testVar)