# TechGig Semifinal Solutions 2021

Java solutions to the two problem statements from the TechGig Code Gladiators 2021 semifinal round.

## Repository Structure

```
.
├── Problem1-statement      # Problem statement for Problem 1 (Jazzy's Balls)
├── Solution1.java          # Java solution for Problem 1
├── Problem2-statement      # Problem statement for Problem 2 (Girlfriend's Problem)
├── Solution2.java          # Java solution for Problem 2
└── README.md
```

## Problem 1: Jazzy's Balls

**File:** [`Problem1-statement`](./Problem1-statement) | [`Solution1.java`](./Solution1.java)

Jazzy has N packets of balls, each containing some number of balls arranged linearly. In one move, he can divide a packet (or group) into any number of equal-sized subgroups. He can only "play" with a ball once it is on its own (a group of size 1), and doing so also counts as a move. Given the number of balls in each packet, find the **maximum number of moves** required for Jazzy to get to play with every single ball.

**Approach:** Memoized recursion (DP) combined with a sieve of Eratosthenes to identify primes up to 10⁶ quickly (a prime `n` can only ever be split into `n` singleton groups, contributing `n + 1` moves). For composite `n`, the code tries every divisor `i` of `n`, recursively computes the best move count for splitting into `i` groups of `n/i` (or vice versa), and takes the maximum over all divisor pairs, caching results in a `HashMap` to avoid recomputation.

**Status:** Passes all test cases (100/100), per the in-code comment.

## Problem 2: Girlfriend's Problem

**File:** [`Problem2-statement`](./Problem2-statement) | [`Solution2.java`](./Solution2.java)

Given a bidirectional, weighted graph of `m` houses and `n` roads, find the shortest path from house `1` (source) to house `m` (destination). If no path exists, print `"NOT POSSIBLE"`.

**Input format:**
```
m n
u1 v1 cost1
u2 v2 cost2
...
un vn costn
```

**Approach:** Dijkstra's shortest-path algorithm implemented with a `PriorityQueue` (min-heap) over `(node, distance)` pairs, adjacency represented as a dense `int[][]` cost matrix.

**Status:** Scored 80/100, per the in-code comment.

## Running the Solutions

Both solutions read input from `stdin` and write output to `stdout`.

```bash
# Problem 1
javac Solution1.java
java Main < input1.txt

# Problem 2
javac Solution2.java
java girfriend < input2.txt
```

## Notes

- These were competitive-programming submissions written under contest time pressure, so the code favors working solutions over polish (e.g. some commented-out experimental code from Problem 1 is left in place, and class names don't always match file names).
- Contributions, refactors, or alternative approaches are welcome via pull request.
