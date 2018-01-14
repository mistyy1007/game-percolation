# game-percolation
A two-dimensional model of percolation. 


When it rains, water soaks into the ground. When sand grains are packed tightly, water doesn't soak as far down.
"Water" arrives at the top of this array, so that every empty location in the first row becomes filled with water.
Water will only flow down or horizontally, never up. For each succeeding row, if an empty space has water directly above it, or horizontally adjacent to it, it also fills with water. At each move, water can flow any distance horizontally.

If the ground is packed tightly with sand, water will not seep very far down into it. If the ground is very loosely packed, water will almost certainly seep all the way down. For an NxN container, there is some packing probability p such that water has exactly a 50% probability of seeping all the way to the bottom row. 
This code is to find that probability. My program is able to do this in well under a minute.
