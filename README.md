# jjsonresume

## Building

`mvn install`

## Testing

`mvn test`

## Running

### Test HTML generation

`mvn exec:java -Dexec.mainClass="me.henryfbp.generator.ResumeGeneratorHTML"`

### Test LaTeX generation

`mvn exec:java -Dexec.mainClass="me.henryfbp.generator.ResumeGeneratorLaTeX"`
