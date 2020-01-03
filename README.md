# library
Library management system
- 3 access levels (reader, librarian, administrator)
 - all CRUD operations on entities
 - utilization of jdbc (postgresql) - cooperation with ElephantSQL database

![entryPanel](https://github.com/MartaDylewska/Library/blob/master/src/main/resources/EntryPanel.PNG)

After logging in as a reader, all available options are listed.

![readerEntry](src/main/resources/readerEntryPanel.PNG)

Reader can join library's event...

![readerJoinEvent](src/main/resources/readerJoinEvent.PNG)

...check his reservations and lendings...

![readerReservedBooks](src/main/resources/readerReservedBooks.PNG)

...search for book using different criteria and reserve it.

![readerSearchForBook](src/main/resources/readerSearchBook.PNG)

Librarian interface after logging looks as shown below:

![librEntry](src/main/resources/librarianEntryPanel.PNG)

Librarian can add a new book...
![librAddBook](src/main/resources/librarianAddBook.PNG)

... accept return and lend a book from a list of reservations (if number of borrowed books <=3)

![librAcceptReturns](src/main/resources/librarianAcceptReturns.PNG)

Administrator entry panel is shown below: 

![adminEntry](src/main/resources/adminEntryPanel.PNG)

Administrator can manage users accounts, for example delete librarian account: 

![adminDelLibr](src/main/resources/adminDeleteLibrarian.PNG)



