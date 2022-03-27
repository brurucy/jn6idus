### Spine

#### What is the Spine?

The Spine is an Ordered Set, that is, the equivalent collections are`SkipList` and `TreeSet`.

Why you should use it is that it is **much** faster, while also providing the possibility to **get an element by their position**.

For instance, let's say that you have a TreeSet.

Internally, everything is ordered, so, naturally, one might want to know what is the k-th element.

However, neither `TreeSet` nor `SkipList` provide this functionality.

#### Benchmarks

##### TreeSet and SkipList

I have provided some simple benchmarks alongside the tests.

The benchmarks test the performance of the Spine, TreeSet and SkipList.

The Spine can do everything that TreeSet and SkipList do, but more(getting and deleting the i-th element).

One important point is the `vertebraSize`, which is the number next to the Spine.

This represents the internal bucket size of the structure that makes up the spine.

I highly recommend leaving the number as `1024`.

The reasons as to why `1024` works best are unknown :).

Feel free to change it. In the benchmark file I have commented out the different measurements that explore different 
`vertebraSize`, thus, you can uncomment that and see for yourself how does that impact the different operations.

Here are the benchmark results when ran on my M1 Pro.

```
Add - Sequential
	Spine 1024 - Time taken 743 ns/op 
	Skip List - Time taken 625 ns/op 
	Tree Set - Time taken 660 ns/op 
Contains - Sequential
	Spine 1024 - Time taken 273 ns/op 
	Skip List - Time taken 397 ns/op 
	Tree Set - Time taken 131 ns/op 
Remove - Sequential
	Spine 1024 - Time taken 219 ns/op 
	Skip List - Time taken 294 ns/op 
	Tree Set - Time taken 184 ns/op
```

Unsurprisingly, the Spine sits between the TreeSet and the SkipList with regards to sequential operations.

This is the most unrealistic scenario, and assumes that the user `always` inserts in an ordered fashion, defeating the purpose
of using an ordered data structure in the first place.

```
Add - Random
	Spine 1024 - Time taken 1607 ns/op 
	Skip List - Time taken 2777 ns/op 
	Tree Set - Time taken 1478 ns/op 
Contains - Random
	Spine 1024 - Time taken 959 ns/op 
	Skip List - Time taken 2779 ns/op 
	Tree Set - Time taken 964 ns/op 
Remove - Random
	Spine 1024 - Time taken 581 ns/op 
	Skip List - Time taken 1940 ns/op 
	Tree Set - Time taken 984 ns/op 
```

This is how **most** workloads work, with random access.

As it can be seen, the Spine performs either as well as the TreeSet, or up to **twice** as good, in deletions.

```
Get the i-th element - Order Irrelevant
	Spine 1024 - Time taken 393 ns/op 
	Skiplist - Time taken 95147400 ns/op 
	TreeSet - Time taken 40029870 ns/op
```

Now, this is where the Spine shines.

It is almost **10** orders of magnitude faster than the rest.

In order to get the i-th element of a SkipList or a TreeSet, one has to **transform it into an array**, and then fetch the element.
Meanwhile, the Spine has a very lightweight index that allows one to fetch the i-th element in O(B) time, with B being the `vertebraSize`, in contrast to 
using `toArray()`, which is `O(n)`.

##### Priority Queue

The Spine can also be used in place of a Priority Queue, with complexity O(B) for **poll**, versus O(1) for the priority queue.

One of the advantages that the spine can provide, is of being able to pop both the maximum and the minimum with the same complexity.

```
Add - Random
	Spine 1024 - Time taken 1236 ns/op 
	PriorityQueue - Time taken 74 ns/op 
Contains - Random
	Spine 1024 - Time taken 230 ns/op 
	PriorityQueue - Time taken 696057 ns/op 
Peek - Random
	Spine 1024 - Time taken 12 ns/op 
	PriorityQueue 1024 - Time taken 9 ns/op 
Poll - Random
	Spine 1024 - Time taken 204 ns/op 
	PriorityQueue 1024 - Time taken 765 ns/op 
```

As it can be seen, unless your usage of `PriorityQueue` is heavily centered around `Add`, there is very little point to choosing it over `Spine`.