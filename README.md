# Estimate The Percolation Threshold Using UnionFind QuickFind And QuickUnion
Given a composite systems comprised of randomly distributed insulating and metallic materials: what fraction of the materials need to be metallic so that the composite system is an electrical conductor? Given a porous landscape with water on the surface (or oil below), under what conditions will the water be able to drain through to the bottom (or the oil to gush through to the surface)? Scientists have defined an abstract process known as percolation to model such situations.

While nobody has successfully found the mathematical approach to solve the percolation problem, the result can be estimated by using computational power. In this notebook, I estimated the percolation thresholds by using three different algorithms: UnionFind, QuickFind, and QuickUnion, and compared their process times. 

## Version
- Java version 8.0

## Model
The model is defined as follow:
- The system is simulated as a NxN grid of sites, initially set as "blocked".
- At every step, a random site can be "open", and if any of its neighboring site is also "open", they will be connected.
- They system is said to be "percolated" if the top of the grid and the bottom of the grid are connected by open sites.

![image](percolation.PNG)

*empty open site: open site not connected to top; full open site: open site connected to top*

## Result
The results are shown as follow, where
- N: number of row/column of the grid
- mean_percolation_threshold: the threshold that the system becomes percolated. e.g. if 200 sites out of 400 sites were open when the system percolates, the threshold is 200/400 = 0.5. Each value is shown as the mean of 100 independent trails.
- union_find_time (ms): process time using UnionFind algorithm in microseconds.
- quick_union_time (ms): process time using QuickUnion algorithm in microseconds.
- quick_find_time (ms): process time using QuickFind algorithm in microseconds.


![image](UnionFind.PNG)

The percolation threshold, no matter the size of the grid, was around 59%. It can be noticed that when N doubles, the number of operation needed should increase by a factor of 4 (2 * 2), and the process time of UnionFind algorithm also increased by a factor of ~4. Therefore UnionFind algorithm scales proportional to N^2. QuickUnion algorithm increased not by a fixed factor, due to the natural randomness in the algorithm, but the factor is definitely larger than 4. QuickFind algorithm is even slower: it increased by a factor of ~14. That means QuickFind algorithm scales proportinal to N^4. When dealing with 1600x1600 grid, UnionFind algorithm is 1300 times faster than QuickFind. This shows the significance of having a better algorithm for processing heavy tasks.
