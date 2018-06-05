# sale-point

To launch project in Intellij IDEA please follow steps below:
1. use 'git clone' command
2. set project SDK: 1.8 using File|Project Structure...
3. add gradle support using View|Tool Windows|Gradle|attach 

Recrutation task:<br />
Implement a simple point of sale.<br />
Assume you have:<br />
– one input device: bar codes scanner<br />
– two output devices: LCD display and printer<br />
Implement:<br />
– single product sale: products bar code is scanned and:<br />
– if the product is found in products database than it's name and price is printed on LCD
display<br />
– if the product is not found than error message 'Product not found' is printed on LCD
display<br />
– if the code scanned is empty than error message 'Invalid bar-code' is printed on LCD
display<br />
– when 'exit' is input than receipt is printed on printer containing a list of all previously
scanned items names and prices as well as total sum to be paid for all items; the total sum is
also printed on LCD display<br />
Rules:<br />
– use only SDK classes and your favorite test libraries<br />
– mock/stub the database and IO devices<br />
– concentrate on proper design and clean code, rather than supplying fully functioning
application<br />
