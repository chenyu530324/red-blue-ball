package com.jichen.redblueball.etl.configuration;

import com.jichen.redblueball.model.annotations.Etl;
import com.jichen.redblueball.model.annotations.Killer;
import com.jichen.redblueball.model.annotations.Predictor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static org.springframework.core.io.support.ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX;
import static org.springframework.util.ClassUtils.CLASS_FILE_SUFFIX;

@Component
public class ClassScannerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassScannerService.class);
    private static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";
    private static final boolean DEBUG_ENABLED = LOGGER.isDebugEnabled();

    @Value("${base.package.name}")
    private String basePackage;

    @Autowired
    private ResourcePatternResolver resourcePatternResolver;

    public List<Killer> scanKillers() {
        List<Class> classList = identifyAllClasses();
        if (null == classList) {
            return null;
        }
        return classList.stream().map(this::getKillerAnnotation).filter(Objects::nonNull).collect(toList());
    }

    public List<Predictor> scanPredictors() {
        List<Class> classList = identifyAllClasses();
        if (null == classList) {
            return null;
        }
        return classList.stream().map(this::getPredictAnnotation).filter(Objects::nonNull).collect(toList());
    }

    public List<Etl> scanEtls() {
        List<Class> classList = identifyAllClasses();
        if (null == classList) {
            return null;
        }
        return classList.stream().map(this::getEtlAnnotation).filter(Objects::nonNull).collect(toList());
    }

    private Etl getEtlAnnotation(Class aClass) {
        Annotation annotation = aClass.getAnnotation(Etl.class);
        if (null != annotation) {
            return AnnotationUtils.getAnnotation(annotation, Etl.class);
        }
        return null;
    }

    private Predictor getPredictAnnotation(Class aClass) {
        Annotation annotation = aClass.getAnnotation(Predictor.class);
        if (null != annotation) {
            return AnnotationUtils.getAnnotation(annotation, Predictor.class);
        }
        return null;
    }

    private Killer getKillerAnnotation(Class aClass) {
        Annotation annotation = aClass.getAnnotation(Killer.class);
        if (null != annotation) {
            return AnnotationUtils.getAnnotation(annotation, Killer.class);
        }
        return null;
    }

    private List<Class> identifyAllClasses() {
        Assert.notNull(basePackage, "Base package name could not be empty");
        String packageSearchPath = CLASSPATH_ALL_URL_PREFIX + resolveBasePackage(basePackage) + '/' + DEFAULT_RESOURCE_PATTERN;
        Resource[] resources;
        try {
            resources = resourcePatternResolver.getResources(packageSearchPath);
        } catch (IOException e) {
            LOGGER.error(format("Get resource failed, base package is %s", basePackage));
            return null;
        }
        List<File> fileList = Arrays.stream(resources).map(this::convertResourceToFileHandle).filter(Objects::nonNull).collect(toList());
        fileList.forEach(file -> LOGGER.debug(format("Identify class %s", file.getPath() + File.separator + file.getName())));
        boolean infoEnabled = LOGGER.isInfoEnabled();
        if (infoEnabled) {
            LOGGER.info(format("Count file size is %s", fileList.size()));
        }
        List<Class> classList = convertFileToClasses(fileList);
        if (DEBUG_ENABLED) {
            classList.forEach(aClass -> LOGGER.info(format("Identified class %s", aClass.getName())));
        }
        return classList;
    }

    private List<Class> convertFileToClasses(List<File> fileList) {
        List<String> filePathList = fileList.stream().map(file -> getClassPackagePath(file.getPath())).collect(toList());
        return filePathList.stream().map(this::getClassByFilePath).filter(Objects::nonNull).collect(toList());
    }

    private Class<?> getClassByFilePath(String path) {
        try {
            return Class.forName(path);
        } catch (ClassNotFoundException e) {
            LOGGER.error(format("Get class info failed, class file path is %s", path));
            return null;
        }
    }

    private File convertResourceToFileHandle(Resource resource) {
        try {
            return resource.getFile();
        } catch (IOException e) {
            LOGGER.error("Convert resource to file failed, resource name is %s ", resource.getFilename(), e.getMessage());
            return null;
        }
    }

    private String resolveBasePackage(String basePackage) {
        return ClassUtils.convertClassNameToResourcePath(basePackage);
    }

    private String getClassPackagePath(String filePath) {
        String packageSearchPath = ClassUtils.convertClassNameToResourcePath(basePackage);
        int indexOf = filePath.indexOf(packageSearchPath);
        return ClassUtils.convertResourcePathToClassName(filePath.substring(indexOf, filePath.length()).replace(CLASS_FILE_SUFFIX, ""));
    }
}
