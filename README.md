# Exercise 1

Write or describe an algorithm that prints the whole integer numbers to the console, start
from the number 1, and print all numbers going up to the number 100.
However, when the number is divisible by 3, do not print the number, but print the word
'Visual'. If the number is divisible by 5, do not print the number, but print 'Nuts'. And for all
numbers divisible by both (eg: the number 15) the same, but print 'Visual Nuts'.
How will you keep this code safe from bugs? Show how you would guarantee that this code
keeps working when developers start making small feature adjustments. (Maybe we would
want to print the first 500 numbers, ...).

# Exercise 2

Image you have a set of data in JSON, describing official languages spoken by countries, as
such:
```json
[
  {
  "country":"US",
  "languages": ["en"]
  },
  {
  "country":"BE",
  "languages": ["nl","fr","de"]
  },
  {
  "country":"NL",
  "languages": ["nl","fy"]
  },
  {
  "country":"DE",
  "languages": ["de"]
  },
  {
  "country":"ES",
  "languages": ["es"]
  }
]
```
Write a function in Java that:

- returns the number of countries in the world
- finds the country with the most official languages, where they officially speak German (de).
- that counts all the official languages spoken in the listed countries.
- to find the country with the highest number of official languages.
- to find the most common official language(s), of all countries.
