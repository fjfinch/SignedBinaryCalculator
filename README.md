# SignedBinaryCalculator
Calculating signed (negative) binary numbers. There are five representations in this script:
(positive) unsigned
Sign-Magnitude  
One's complement
Two's complement
Offset binary (excess-k); where `k=2^(bits-1)`

## Usage 
1) Enter a number within range. Java long = [-2^63 - 2^63-1].
2) Enter the amount of minimum bits to represent the answers. This is partially necessary because signed algorithms need a signed bit to represent the negative bit. This is most of the time the MSB (most significant bit). 
If the given amount of bits is to low or is set to 0, it will automatically overwrite to the minimum amount of bits it needs to have.

Offset binary is a bit different and will give a '-' when a faulty amount of bits is given. 

## why
I noticed online binary calculators give (partially) wrong answers when working with the bare minimun representable bits or when working with the number 0. 
So I wanted to add my possibly broken script to this colllection :) I will try to fix any bug I find.

## ToDo
I will be working on a python version.
