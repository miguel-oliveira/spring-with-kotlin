package miguel.oliveira.demo.kotlin.record

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class Record(val beanName: String)
