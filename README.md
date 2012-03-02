# linkedin-connections
======================

A library for working with exported connection lists from LinkedIn.

Supports Yahoo and Outlook (both) formats.

### Build

    $ lein uberjar

## Usage

To print your LinedIn connection emails export to either one of the Outlook formats or the Yahoo format.

    $ java -jar linkedin-connections-1.0.0-standalone.jar FILE.csv

To create a file of just the connection's emails try the following.

    $ java -jar linkedin-connections-1.0.0-standalone.jar FILE.csv > linkedin-emails.csv

## License

Distributed under the Eclipse Public License, the same as Clojure.
