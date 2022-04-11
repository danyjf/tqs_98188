# Quality Gate

The project passed the quality gate with 1 bug, 0 vulnerabilities, 1 security hotspot and 27 code smells.


# Issues

| Issue       | Problem Description |  How To Solve |
|-------------|---------------------|---------------|
|Bug          |A `Random` object is created every time a random value is needed|Store a single `Random` object and reuse it|
|Code smell   |The counter variable of a for loop is being incremented inside the for loop body|Replaced for loop with a while loop|