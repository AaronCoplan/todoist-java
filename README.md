# todoist-java

Todoist REST API Java Client supporting all features found in the [Todoist REST API Reference](https://developer.todoist.com/rest/v8/).

# Examples

```java
Todoist todoist = new Todoist("***TOKEN***");
List<Project> myProjects = todoist.getAllProjects();
for(Project p : myProjects) {
    System.out.println(p);
}
```
