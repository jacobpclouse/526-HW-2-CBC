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
import random # IV generation use

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

# --- Function to turn plaintext/ciphertext String into binary  ---    # below is a snipit from GeeksForGeeks:
def convert_string_to_binary(input_string):
    print("Converting String to Binary: \n")
    # Encode the string into bytes using utf-8 encoding
    bytes = input_string.encode('utf-8')
    # Convert each byte to its binary representation
    binary = [bin(byte)[2:].zfill(8) for byte in bytes]
    # Join the binary representations of each byte into a single string
    binary_string = ''.join(binary)
    return(binary_string)

# --- Function to turn binary into plaintext/ciphertext string ---    # below is a snipit from GeeksForGeeks:
def return_binary_to_string(input_binary_data):
    print("Returning Binary to String: \n")
    # Split the binary string into a list of 8-bit binary representations
    binary_list = [input_binary_data[i:i+8] for i in range(0, len(input_binary_data), 8)]
    # Convert each binary representation to its decimal equivalent
    decimal_list = [int(binary, 2) for binary in binary_list]
    # Convert the sequence of decimal values back to the original string
    byte_string = bytes(decimal_list)
    original_string = byte_string.decode('utf-8')
    return original_string


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


# --- Function to Generate IV  ---
def generate_IV(plaintext):
    # will take in input from user outside of function and then pass it in
    print("Generating IV\n")
    # using the length of plaintext to generate random one digit numbers from 0 to 9, need to store and output
    IV_key= ''
    for letters in plaintext:
        current_key_value = str(random.randint(0,9))
        IV_key += current_key_value
        #print(f"Letter: {letters}, Key value: {current_key_value}")
    # Convert IV to binary numbers
    converted_IV = convert_string_to_binary(IV_key)
    return IV_key, converted_IV


# --- Function to Encrypt CBC --- are we going to divide up the blocks beforehand or inside of this? number of rounds determined by the plaintext
def encrypt_cbc(datetime,plaintext,iv):
    # Initialize Encryption Function
    print(f"Starting CBC Encryption at: {datetime}")

    # BRING IN THE WHOLE PLAINTEXT AND ITERATE THROUGH IT INSIDE THIS FUNCTION

    # setting this up so we can keep IV and still update value 
    cipher_XOR_Value = iv
    lengthOfPlaintext = len(plaintext)  # need to have converted this to the binary rep beforehand
    lengthOfXOR = len(cipher_XOR_Value)
    After_XOR = ''

    if(lengthOfPlaintext == lengthOfXOR):
        print(f"Blocks are both {lengthOfXOR}, can procceed")

        # XOR function
        for index, character in enumerate(plaintext):
            corresponding_value = cipher_XOR_Value[index]
            # print(f"{character} at index {index}, XOR Value: {corresponding_value}")
            # print(f"Type character: {type(character)}, type XOR {type(corresponding_value)}, Int Char {type(int(character))}")
            After_XOR += str(int(character) ^ int(corresponding_value))

        print(f"After XOR: {After_XOR}")

        # AES encryption
        


        cipher_XOR_Value = After_XOR # Setting new IV to the value of the


    
    else:
        print("Error: Length of Plaintext Block and Length of XOR block do not match.")


    # Creating Array to Store Ciphertext
    #encryption_ciphertext = []


# 1 - break it down - just do each part of cbc seperatly, xoring, etc
# 2 - try it with one block, check output, write decryption for one block, check output
# 3 - try adding more blocks, one by one, see if it works, etc
# BE WARY OF DIVIDING UP THE MESSAGE - take note of when you do it and how you do it



# =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
# MAIN PROGRAM
# =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-


# Grabbing DateTime
grab_time = defang_datetime()

# need to have the b before the string 
#testVar = b'break it down boi'
#break_it_down(testVar)

# setting plaintext
our_plaintext = "J"
# converting plaintext to binary
binary_plaintext = convert_string_to_binary(our_plaintext)
# generating IV of same length as our plaintext
string_IV_key, binary_IV_Key = generate_IV(our_plaintext)

print(f"binary_plaintext: {binary_plaintext}, Binary IV: {binary_IV_Key}")
encrypt_cbc(grab_time, binary_plaintext, binary_IV_Key)






# input_String = 'l2'
# converted = convert_string_to_binary(input_String)
# print(converted)
# reverted = return_binary_to_string(converted)
# print(reverted)