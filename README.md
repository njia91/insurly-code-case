# Insurely code case

We have put together a skeleton for a backend Java service, but it doesn't do much today.
Your assignment is to go through and implement as many of the following issues as you can within max 3-4 hours.
Try to finish the issues that you have started.

In the resources folder you are given 52 example insurances of each type. Which has different amounts
of data present. The last insurance has the maximum amount of data, and the second to last has the minimum 
amount of data given.

Your tasks are
- Given two insurance of the same type, list which fields are missing from one of them - if any.
- Given one insurance, and insurances saved since previously (e.g. the catalog of insurances in the resource folder),
 find out which fields are missing in the given insurance.
- Create an API that takes an insurance as the request body, and returns the fields missing.
- There is currently no test code, but ideally some or all of the functionality should be tested.

## Building and running

- mvn spring-boot:run
