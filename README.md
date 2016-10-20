# linkedin-connections
======================

A library for working with exported connection lists from LinkedIn. Supports Yahoo and Outlook (both) formats.

The export from LinkedIn includes the First Name, Last Name, Email, Title and Company for each of your contacts with 64 otther empty columns in the export file.

First feature implemented here is to parse the files and return the email for each contact. With this you can quickly build a file with just the emails for each of your contacts.

## Build

    $ lein uberjar

## Usage

	First download your LinkedIn connections to a file FILE.csv by exporting them to either of the Outlook formats or the Yahoo format. The export connections feature is available on LinkedIn at http://www.linkedin.com/addressBookExport.

	To parse your connection list and print the emails to stdio use the following command.

	$ java -jar target/linkedin-connections-1.0.1-standalone.jar FILE.csv

	To create a file of your connection's emails use the following.

	$ java -jar target/linkedin-connections-1.0.1-standalone.jar FILE.csv > linkedin-emails.csv

## Installation

`linkedin-connections` is available as a Maven artifact via [Clojars](http://clojars.org/org.clojars.blucas/linkedin-connections).

## Versions

### 1.0.2

- bump version for clojars

### 1.0.1

- Minor updates

### 1.0.0

- Original release

## License

Distributed under the Eclipse Public License, the same as Clojure.
