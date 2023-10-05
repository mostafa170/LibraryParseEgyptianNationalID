# Library Parse Egyptian National ID
Library to parse Egyptian National id automatic from A to Z

# Enter any national id return automatic 
- birthCentury
- birthYear 
- monthOfBirth
- dayOfBirth
- birthGovernorate
- sequenceInComputer
- gender

Library support English and Arabic language 

# Validations and checks
- birthCentury (Vaild or not)
- birthYear (Vaild from 1800 to 2024 or not "return string error")
- monthOfBirth (Vaild from 1 to 12 or not "return string error")
- dayOfBirth (Vaild from 1 to 30 "to April , June , September and November " & or from 1 to 29 "to February " & "from 1 to 31 January , March,May,July,August, October ,December" not "return string error")
- birthGovernorate (Vaild or not "return string error")
- sequenceInComputer return 3 numbers
- gender (male or female)

# The national ID consists of the following:

|2|90|01|01|21|345|6|7

|A  |B   |C   |D   |E  | F  |G|

- A -> The century:A=1 From (1800-1899), A=2 From (1900-1999), A=3 From (2000-2099) 
- B~D (Date of birth): B -> Year of birth C -> Month of birth D -> Day of birth
- E -> Governorate code ex: {21: "Giza"}
- F -> sequenceInComputer code ex: {345}
- G -> Unique code. (Odd is male, Even is female)
