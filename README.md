# FitCheck — Project 2 Prototype

## Overview
FitCheck is a Java console application for planning a Monday–Friday rotation of shoes and outfits. Users can view a preloaded closet, assign a shoe and an outfit to each weekday, view their weekly plan, replace a planned fit, or clear a day.

The `Closet` uses the same generic `LinkedChain<T>` class to store both `Sneaker` and `Outfit` objects (`LinkedChain<Sneaker>` and `LinkedChain<Outfit>`). The application accesses these collections through public methods rather than manipulating linked nodes directly.

## How to compile and run
1. Open the source files in an Eclipse Java project using Java 17.
2. Place the `.java` files from `src/` in the project's `src` folder (default package).
3. Run `FitCheckApp.java` as **Java Application**.
4. Follow the console menu to view the closet, build a plan, view it, replace a fit, clear a day, or exit.

## How to run the tests
1. In Eclipse, add **JUnit 5** to the project's Java Build Path if it is not already present.
2. Run each `*Test.java` file using **Run As > JUnit Test**, or run all test classes together.
3. Verify that tests execute and pass. The test classes are `LinkedChainTest`, `SneakerTest`, `OutfitTest`, `ClosetTest`, `WeeklyPlanTest`, and `FitCheckAppTest`.



## Reflection

### What changed from the specification?

Our final FitCheck prototype stayed close to our original specification. We implemented the planned classes, reused `LinkedChain<T>` for both sneakers and outfits, and created a Monday-through-Friday outfit planner. One design decision we made was to use a `Day` enum instead of representing weekdays as strings. This gave us a consistent way to represent Monday through Friday, reduced the chance of invalid day values, and made it easier for `WeeklyPlan` to manage daily selections. We also expanded the predefined closet to seven shoes and seven outfits to give users more combinations, a stretch goal. Additionally, we used a shared method for building and replacing weekly fits to avoid repeating code. Our tests were standardized to JUnit 5 so they could run together. We also kept user-created closet items as a future feature, as permitted by our specification. Overall, these changes helped us simplify the implementation while keeping the core functionality and class responsibilities consistent with our original design.

### What changed in LinkedChain?

Our team modified the instructor-provided `LinkedChain<T>` class to better support FitCheck. We added a `tail` reference so items could be appended to the end of the chain in O(1) time while maintaining insertion order. We also added indexed insertion and removal methods, allowing elements to be accessed and modified at specific positions. Additionally, we moved the `Node` class into its own file and changed the `add()` method to return `void` instead of `boolean`. We changed the handling of null values so that null additions are ignored rather than throwing an exception. Some methods from the original implementation, including `addToEnd()`, `toArray()`, and `toString()`, were removed or replaced. These changes helped us use the same generic linked chain for both sneakers and outfits while keeping the application's logic separate from the data structure.

### What happened to the unknowns?
The team resolved how to represent weekdays (`Day` enum) instead of raw strings, how to store different closet item types with one generic chain, and how to handle invalid numeric menu input. The app also demonstrates how `Closet` and `WeeklyPlan` interact with the console menu. Remaining questions include whether to let users manage the closet inventory directly and whether to support saving plans between runs.

### What does not work yet?
The console menu does not let users add or remove sneakers or outfits from the closet inventory. The plan is not saved after the application closes. Automatic shoe/outfit recommendations and multiple saved rotations are not implemented. (all stretch goals)

## Next Steps

1. **Ari — finish the MVP interface:** Review the console workflows and, if needed for the final, add user-driven closet inventory changes and corresponding invalid-input handling.
2. **Aidan — generic collection review:** Compare `LinkedChain<T>` against the instructor-provided version, document any modifications, and check empty-collection and invalid-index behavior.
3. **Asher — model review:** Confirm `Sneaker` and `Outfit` validation, display, and equality behavior; add tests for any uncovered edge cases.
4. **Armaan — closet and planner review:** Confirm `Closet` and `WeeklyPlan` behavior when replacing, removing, or clearing items and days, including edge cases.
5. **All team members — final verification:** Run the entire JUnit 5 suite, manually test bad console input, and confirm the program compiles and has saved data from a fresh run, and lastly implement stretch goals.

### Stretch-goal decisions
- **Committed for consideration after MVP checks:** Allow users to add their own sneakers and outfits through the console menu. Automatic outfit/shoe matching, multiple saved weekly rotations, and day-swapping functionality.
- None dropped at the moment.
- **Already present:** Additional shoe and outfit details such as brand, color, style, and occasion.

### Additional tests to add or verify
Test empty closet behavior; null sneaker/outfit additions; invalid closet indices; removal of a missing item; swapping a weekday; invalid numeric and nonnumeric menu input; duplicate elements in `LinkedChain`. 
## Attribution

| `LinkedChain.java` Aidan - Generic linked-chain implementation and adaptation/review; confirm exact changes to starter
| `Node.java` Aidan - Node implementation used by `LinkedChain`
| `LinkedChainTest.java` Aidan - Collection tests

| `Sneaker.java` Asher - Sneaker Class
| `SneakerTest.java` Asher - Sneaker tests
| `Outfit.java` Asher - Outfit Class
| `OutfitTest.java` Asher - Outfit tests

| `Closet.java` Armaan - Closet implementation using both generic chains
| `ClosetTest.java` Armaan - Closet tests
| `Day.java` Armaan - Weekday enum
| `WeeklyPlan.java` Armaan - Weekday fit storage, retrieval, replacement, and clearing
| `WeeklyPlanTest.java` Armaan - Weekly planner tests

| `FitCheckApp.java` Ari - Console menu, input validation, app integration, and preloaded examples
| `FitCheckAppTest.java` Ari - Console application tests and JUnit 5 integration

| `README.md` All members - Project instructions, reflection, next steps, attribution


## GenAI disclosure
The team used GenAI tools during parts of the project. ChatGPT assisted with discussion of integration/debugging and JUnit 5 test conversion when we all had different JUnit test versions. Claude was used by some team members for design choices, debugging, test suggestions, or implementation support, as described in their source-file disclosures. All team members are responsible for reviewing and understanding the submitted work.
