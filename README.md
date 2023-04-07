# WidgetCandy

## Detekt

This project contains [Detekt](https://github.com/detekt/detekt) for static code analysis.  
We ensure detekt usage by adding a git pre-push commit hook.  
To enable this execute the following from the project root folder.

```
$ ./scripts/initialize.sh
```

If you want to run it manually, use the gradle command

```
$ ./gradlew detekt
```