(defproject apex-bot "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [com.github.discljord/discljord "1.3.1"]
                 [environ "1.2.0"]]
  :plugins [[lein-environ "1.2.0"]]
  :repl-options {:init-ns apex-bot.core}
  :main apex-bot.core)
