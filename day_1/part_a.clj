(require '[clojure.string :as str])
(require '[clojure.core.reducers :as reducers])
(require '[clojure.java.io :as io])
(def file "input.txt")
(defn process-input
  [rdr]
  (loop [line (.readLine rdr)
         list1 []
         list2 []]
    (if (nil? line)
      [list1 list2]
      (let [nums (str/split line #"\s+")]
        (if (= 2 (count nums))
          (recur (.readLine rdr)
                 (conj list1 (get nums 0))
                 (conj list2 (get nums 1)))
          (recur (.readLine rdr) list1 list2))))))
(with-open [rdr (io/reader file)]
  (let [[list1 list2] (process-input rdr)
        sort1 (sort list1)
        sort2 (sort list2)]
    (println (reducers/fold
               +
               (map #(abs (- (Integer. %1) (Integer. %2))) sort1 sort2)))))
