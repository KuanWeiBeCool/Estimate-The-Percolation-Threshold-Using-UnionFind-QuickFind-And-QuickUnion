# Estimate The Percolation Threshold Using UnionFind QuickFind And QuickUnion
Given a composite systems comprised of randomly distributed insulating and metallic materials: what fraction of the materials need to be metallic so that the composite system is an electrical conductor? Given a porous landscape with water on the surface (or oil below), under what conditions will the water be able to drain through to the bottom (or the oil to gush through to the surface)? Scientists have defined an abstract process known as percolation to model such situations.

While nobody has successfully found the mathematical approach to solve the percolation problem, the result can be estimated by using computational power. In this notebook, I estimated the percolation thresholds by using three different algorithms: UnionFind, QuickFind, and QuickUnion, and compared their process times. 

## Version
- Java version 8.0

## Model
The model is defined as follow:
- The system is simulated as a NxN grid of sites, initially set as "closed".
- At every step, a random site can be "open", and if any of its neighboring site is also "open", they will be connected.
- They system is said to be "percolated" if the top of the grid and the bottom of the grid are connected by open sites.
![image](
