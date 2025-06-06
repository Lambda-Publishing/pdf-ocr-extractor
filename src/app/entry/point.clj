(ns app.entry.point
  "アプリケーションのエントリーポイント。引数を解析して CLI 本体に委譲する。"
  (:require [app.adapter.in.cli :as cli])
  (:gen-class))

(defn -main
  "Usage: clojure -M:run <input.pdf> <output.txt>"
  [& args]
  (let [[in out] args]
    (if (and in out)
      (cli/run in out)
      (println (.-doc (meta #'-main))))))