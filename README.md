# <ins>Mini-Apps</ins>
# A. Prefix and Suffix of List
## Description
The application tries to check whether the provided suffix or prefix satisfies the given list or not by returning 'true' or 'false'.
## 1. Prefix
When we look for the provided prefix it first split the list into the same length of the prefix as we have to check only those elements in a list and then it will check whether it is equal or not.
### <ins>Rules Implemented</ins>
**i. prefix(List1,List2)**<br />
This function is provided with the prefix and the list to give out the result.<br /><br />
**ii. splitPrefix([Head|RemList],I,[Head|Result])**<br />
This function is used to split the **first half** of the list in the exact same length as that of the prefix provided and then return it back so that it can be checked for equality<br /><br />
**iii. equalListPrefix([H1|T1],[H2|T2])**<br />
This function checks whether the 2 provided lists are equal or not. If there is any non-similar element then it will return false else if the function is able to traverse through both the lists completely then it can be said that the provided prefix was correct. <br /><br />

## 2. Suffix
Suffix works the same as a prefix it just takes the second half of the list while splitting rather than the first half as taken in Prefix.

### <ins>Rules Implemented</ins>
**i. suffix(List1,List2)**<br />
This function is provided with the suffix and the list to give out the result.<br /><br />
**ii. splitSuffix([__|RemList],I,Result)**<br />
This function is used to split the **second half** of the list in the exact same length as that of the suffix <br />
provided and then return it back so that it can be checked for equality<br /><br />
**iii. equalListSuffix([H1|T1],[H2|T2])**<br />
This function checks whether the 2 provided lists are equal or not. If there is any non-similar element then <br />
it will return false else if the function is able to traverse through both the lists completely then it can be <br />
said that the provided prefix was correct. <br /><br />

## Screenshots
### <ins>Prefix</ins>
<img src="https://user-images.githubusercontent.com/33955028/140700252-acfc5d23-524d-4f1b-bc28-d9bff45122cd.png" width="220" height="250">

### <ins>Suffix</ins>
<img src="https://user-images.githubusercontent.com/33955028/140700302-cb85e1d0-cfed-4dcb-aa5f-0092f1412730.png" width="220" height="250">
