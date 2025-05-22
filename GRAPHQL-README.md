# GraphQL API Usage

This application provides a GraphQL API. Here's how to use it:

## GraphQL Schema

The schema defines:
- Queries: `hello` and `greeting`
- Mutations: `saveGreeting`
- Subscriptions: `counter`

## Using GraphiQL

Access the GraphQL playground at: [http://localhost:8080/graphiql](http://localhost:8080/graphiql)

## Sample Queries

### Hello Query
```graphql
query {
  hello
}
```

### Greeting Query
```graphql
query {
  greeting(name: "Alice")
}
```

### Save Greeting Mutation
```graphql
mutation {
  saveGreeting(name: "Alice", greeting: "Hey there, Alice!")
}
```

### Counter Subscription
```graphql
subscription {
  counter
}
```

## Code Generation

The project uses GraphQL code generation to create Java types based on the schema.

To generate code:
```bash
mvn generate-sources
```

Generated code will be in `target/generated-sources/graphql-maven-plugin`. 