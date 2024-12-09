(require '[clojure.string :as str])
(require '[clojure.java.io :as io])

(def file "input.txt")
(defn process-input
  [rdr]
  (loop [line (.readLine rdr)
         list1 ()
         list2 ()]
    (if (nil? line)
      [list1 list2]
      (let [nums (str/split line #"\s+")]
        (if (= 2 (count nums))
          (recur (.readLine rdr)
                 (conj list1 (Integer. (get nums 0)))
                 (conj list2 (Integer. (get nums 1))))
          (recur (.readLine rdr) list1 list2))))))

(defn count-matches
  [haystack needle]
  (loop [matches 0
         head (peek haystack)
         tail (pop haystack)]
    (let [new-matches (if (= head needle) (inc matches) matches)
          new-head (peek tail)]
      (if (nil? new-head)
        new-matches
        (recur new-matches new-head (pop tail))))))

(defn similarity-score
  [list1 list2]
  (loop [score 0
         head1 (peek list1)
         tail1 (pop list1)]
    (let [new-score (+ score (* head1 (count-matches list2 head1)))
          new-head (peek tail1)]
      (if (nil? new-head) new-score (recur new-score new-head (pop tail1))))))

(with-open [rdr (io/reader file)]
  (let [[list1 list2] (process-input rdr)]
    (println (similarity-score list1 list2))))
