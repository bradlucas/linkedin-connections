(ns linkedin-connections.core
  (:require [clojure-csv.core :as csv])
  (:require [clojure.java.io :as io])
  (:gen-class :main true))

;; test files
(def linkedin "/Users/brad/downloads/linkedin-connections/linkedin_connections_export_microsoft_outlook.csv")
(def linkedin2 "/Users/brad/downloads/linkedin-connections/linkedin_connections_export_outlook_express.csv")
(def yahoo "/Users/brad/downloads/linkedin-connections/linkedin_connections_export_yahoo.csv")

;; ------------------------------------------------------------------------
;; mac osx
;; TODO
;; ------------------------------------------------------------------------

;; ------------------------------------------------------------------------
;; Yahoo format
;;
(defn is-yahoo
  "The Yahoo file header starts with \"First\""
  [header]
  (= "First" (first (first (csv/parse-csv header)))))

;; ------------------------------------------------------------------------
(def yahoo-extract
  " Yahoo extractor function

 |---------+-------|
 | Column  | Index |
 |---------+-------|
 | First   |     0 |
 | Last    |     2 |
 | Email   |     4 |
 | Company |    21 |
 | Title   |    20 |
 |---------+-------|
"
  (juxt
   #(nth % 0)
   #(nth % 2)
   #(nth % 4)
   #(nth % 21)
   #(nth % 20)))

;; ------------------------------------------------------------------------
;;  The Microsoft Outlook and Outlook Express file formats are the same.
;;
(defn is-outlook [header]
  "The Outlook file format starts with \"Title\".
"
  (not (is-yahoo header)))

;; ------------------------------------------------------------------------
(def outlook-extract
  " Outlook extractor function

 |------------+-------|
 | Column     | Index |
 |------------+-------|
 | First Name |     1 |
 | Last Name  |     3 |
 | Email      |     5 |
 | Company    |    29 |
 | Job Title  |    31 |
 |------------+-------|
"
  (juxt
   #(nth % 1)
   #(nth % 3)
   #(nth % 5)
   #(nth % 29)
   #(nth % 31)))

(defmacro get-extractor
  "Return the extractor function based on the type of file determined by looking at the header
 using the is-yahoo and is-outlook functions"
  [header]
  `(if (is-yahoo ~header)
    yahoo-extract
    outlook-extract))

(defn parser [fn row]
  "Using the extractor function return a map created by parsing the row into a vector of values"
  (let [vec (first (csv/parse-csv row))]
    (let [[firstname lastname email company title] (fn vec)]
      {:firstname firstname :lastname lastname :email email :company company :title title})))

(defn first-last-email
  "Return first,last,email from map"
  [map]
  (str (:firstname map) "," (:lastname map) "," (:email map)))

(defn email
  "Return email from map"
  [map]
  (:email map))

(defn get-data [file func]
  "Return list from file by parsing lines into maps and then using func to extract the desired data"
  (with-open [rdr (io/reader file)]
    (let [seq (line-seq rdr)
          ext (get-extractor (first seq))]                       ;; Get the file specific extractor by passing the first line to get-extractor
      (doall (map #(func (parser ext %)) (rest seq))))))

(defn get-emails [file]
  "Return the emails from a given Linked export file."
  (get-data file email))

(defn get-first-last-emails [file]
  "Return firstname, lastname, email"
  (get-data file first-last-email))

(defn get-header
  "Test function to return the first line of the file"
  [file]
  (with-open [rdr (io/reader file)]
    (let [col (first (line-seq rdr))]
      col)))

(defn print-usage []
  (println "linkedin-connections FILE"))

(defn -main
  "Print the emails of the given file to STDIO"
  [& args]
  (if args
    (doseq [email (get-emails (first args))]
      (println email))
    (print-usage)))
