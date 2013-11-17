(defproject spotkuleap "0.1.0-SNAPSHOT"
  :description "Control spotify using clojure-leap API"
  :url "http://github.com/ilkka/clojure-leap"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.0"]
                 [clj-http "0.7.7"]]
  :resource-paths ["leap_lib/LeapJava.jar" "resources"]
  
  :warn-on-reflection true
  
  :jvm-opts  [~(str "-Djava.library.path=leap_lib/:" (System/getenv "LD_LIBRARY_PATH"))]
  :main spotkula.koppi)

