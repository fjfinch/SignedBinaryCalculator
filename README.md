# signedBinaryCalculator
Calculating signed (negative) binary numbers. There are five representations in this script:
* (positive) unsigned
* Sign-Magnitude  
* One's complement
* Two's complement
* Offset binary (excess-k); where `k=2^(bits-1)`

## Context
I noticed online binary calculators give (partially) wrong answers when working with the bare minimum presentable bits or when working with the number 0.
So I wanted to add my, bad and not optimised, script to this colllection :) I will try to fix any bug I find.

One example is the decimal number -8 in one's complement. 8 in binary is 1000. Reversed is 0111. Calculated [here](https://planetcalc.com/747/) with 4 bits. But one's complement is still a representation with a signed bit. You can see [here](https://en.wikipedia.org/wiki/Signed_number_representations) on Wikipedia one's complement, 4 bits, having a [-7,7] range. Also all negative numbers starts with a '1'. So for -8 you need an extra 1 before 0111 -> 10111 (5 bits). See table below.

Also a lot of online calculators give the 'positive' value for 0. And not the 'negative' value. See table below.

| Decimal | One's complement |
| --- | --- |
| +8 | - |
| +7 | 0111 |
| +6 | 0110 |
| +5 | 0101 |
| +4 | 0100 |
| +3 | 0011 |
| +2 | 0010 |
| +1 | 0001 |
| +0 | 0000 |
| -0 | 1111 |
| -1 | 1110 |
| -2 | 1101 |
| -3 | 1100 |
| -4 | 1011 |
| -5 | 1010 |
| -6 | 1001 |
| -7 | 1000 |
| -8 | - |

## Usage
1. Enter a number within range. Java long = [-2^63 - 2^63-1].
2. Enter the amount of minimum bits to represent the answers. This is necessary because some signed algorithms need a signed bit to represent the negative bit. This is most of the time the MSB (most significant bit). 
If the given amount of bits is to low or is set to 0, it will automatically overwrite to the minimum amount of bits it needs to have.

Offset binary is a bit different and will give a '-' when a faulty amount of bits is given. 

## ToDo
* I might make a python version.
