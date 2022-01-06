package reportConfig;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import retryConfig.RetryFailTestListener;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AnnotationTransformer implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryFailTestListener.class);
    }
}