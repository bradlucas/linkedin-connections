# linkedin-connections
======================

A library for working with exported connection lists from LinkedIn. Supports Yahoo and Outlook (both) formats.

The export from LinkedIn includes only the First Name, Last Name, Email, Title and Company for each of your contacts. There are 64 other columns in the export file with no data.

First feature implemented here is to parse the files and return the email for each contact. With this you can quickly build a file with just the emails for each of your contacts.

### Build

    $ lein uberjar

## Usage

First download your LinkedIn connections by exporting them to either of the Outlook formats or the Yahoo format. The export connections feature is available on LinkedIn at http://www.linkedin.com/addressBookExport.

To parse your connection list and print the emails to stdio use the following command.

    $ java -jar linkedin-connections-1.0.0-standalone.jar FILE.csv

To create a file of your connection's emails use the following.

    $ java -jar linkedin-connections-1.0.0-standalone.jar FILE.csv > linkedin-emails.csv

### Installation

`linkedin-connections` is available as a Maven artifact via [Clojars](http://clojars.org/org.clojars.blucas/linkedin-connections).

## License

Distributed under the Eclipse Public License, the same as Clojure.
