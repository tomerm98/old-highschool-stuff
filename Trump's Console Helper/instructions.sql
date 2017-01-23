CREATE TABLE Reporters
(
    Id int IDENTITY(1,1) PRIMARY KEY,
    FirstName varchar(255),
    LastName varchar(255),
    Address varchar(255)
);


CREATE TABLE FakeNews
(
    Id int IDENTITY(1,1) PRIMARY KEY,
    Title varchar(255),
    ReporterId int FOREIGN KEY REFERENCES Reporters(Id)
);


/*
  What would you like to do Mr. Trump ?
> insert reporter
  Please Enter a First Name:
> Magyn
  Please Enter a Last Name:
> Kelly
  Please enter an Address:
> Somewhere Avenue 123
  Reporter Successfully Inserted
  What would you like to do Mr. Trump ?
> insert news
  Please Enter a Title:
> Trump is bad
  Please Enter a Reporter ID:
> 1
  News Successfully Inserted
  What would you like to do Mr. Trump ?
> list reporters
  ID: 1, Name: Magyn Kelly, Address: Somewhere Avenue 123
  What would you like to do Mr. Trump ?
> list news by id
  Please enter a Reporter ID:
> 1
  ID: 1, Title: Trump is bad, ReporterId: 1
  What would you like to do Mr. Trump ?
> find reporter by news
  Please enter a news title:
> Trump is bad
  ID: 1, Name: Magyn Kelly, Address: Somewhere Avenue 123
> quit
*/
  


