# Setup
The application in order to run correctly needs a GitHub pat in the `local.properties` file.

## 1. Generate the token

In order to generate one, login in your GitHub profile then Settings → Developer settings → Personal access tokens.

A fine-grained token with read only access is enough. You can choose if include private repositories or not.

## 2. Use the token

To use the token open your `local.properties` file and add this line:
```properties
github.token=REPLACE_WITH_YOUR_PAT
```
