# guacamole

[![Release](https://jitpack.io/v/crykn/guacamole.svg)](https://jitpack.io/#crykn/guacamole) ![GWT Compatible](https://img.shields.io/badge/GWT-compatible-informational)

A small collection of some common and **very basic utilities for [libGDX](https://github.com/libgdx/libgdx) games**, which is used in [libgdx-screenmanager](https://github.com/crykn/libgdx-screenmanager) and [pancake](https://github.com/eskalon/pancake).

## Core 

```
dependencies {
    implementation 'com.github.crykn.guacamole:core:$guacamoleVersion'
}
```

* [@Beta](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/annotations/Beta.java): an annotation to denote APIs that may be subject to changes in future releases
* [BooleanConsumer](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/func/BooleanConsumer.java), [FloatConsumer](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/func/FloatConsumer.java), [ShortConsumer](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/func/ShortConsumer.java), [ICallback](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/ICallback.java)
* [ClassUtils](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/ClassUtils.java): contains utilities for dealing with classes
* [ConcatenatedIterator](https://github.com/crykn/guacamole/blob/master/gdx/src/main/java/de/damios/guacamole/ConcatenatedIterator.java): this class aggregates multiple iterators into one
* [DaemonThreadFactory](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/concurrent/DaemonThreadFactory.java): a `ThreadFactory` creating daemon threads
* [Exceptions](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/Exceptions.java): `#throwAsRuntimeException(Exception)`, `#getStackTraceAsString(Exception)`
* [@GwtIncompatible](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/annotations/GwtIncompatible.java): indicates that an API is not compatible with GWT and thus shouldn't be compiled for it
* [IntRange](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/ClassUtils.java)
* [MoreObjects](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/MoreObjects.java): contains helper functions for dealing with objects which are not already included in Java's `Objects` class
* [@Nullable](https://jcp.org/en/jsr/detail?id=305#2): the `@Nullable` annotation from JSR 305 is provided as a transitive dependency 
* [Pair](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/tuple/Pair.java), [Triple](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/tuple/Triple.java), [IntPair](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/tuple/IntPair.java) & [IntTriple](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/tuple/IntTriple.java) implementations (immutable)
* [Preconditions](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/Preconditions.java): has methods like `#checkArgument(boolean, String)`, `#checkState(boolean, String)` & `#checkNotNull(Object, String)`; is slimmer than the commonly known class from [guava](https://github.com/google/guava/wiki/PreconditionsExplained)
* [StopWatch](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/StopWatch.java)
* [ThreadHandler](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/concurrent/ThreadHandler.java): a class to easily offload simple, time-consuming tasks onto threads

<br/>

## gdx & gdx-desktop & gdx-gwt

### gdx & gdx-gwt

**Core project:**

```
dependencies {
    implementation "com.github.crykn.guacamole:gdx:$guacamoleVersion" // guacamole-core is included
}
```

**Html project:**

```
dependencies {
    implementation "com.github.crykn.guacamole:gdx:$guacamoleVersion" // guacamole-core is included
    
    implementation "com.github.crykn.guacamole:core:$guacamoleVersion:sources"
    implementation "com.github.crykn.guacamole:gdx:$guacamoleVersion:sources"
    implementation "com.github.crykn.guacamole:gdx-gwt:$guacamoleVersion"
    implementation "com.github.crykn.guacamole:gdx-gwt:$guacamoleVersion:sources"
}
```

Module file (_GdxDefinition.gwt.xml_):

```
<inherits name="guacamole_gdx_gwt" />
```

**What is added:**

* [DefaultInputProcessor](https://github.com/crykn/guacamole/blob/master/gdx/src/main/java/de/damios/guacamole/gdx/DefaultInputProcessor.java): an `InputProcessor` providing default methods to reduce boilerplate code; is an interface alternative to `InputAdapter`
* [DisposablePool](https://github.com/crykn/guacamole/blob/master/gdx/src/main/java/de/damios/guacamole/gdx/math/pool/DisposablePool.java), [Vector2Pool](https://github.com/crykn/guacamole/blob/master/gdx/src/main/java/de/damios/guacamole/gdx/math/pool/Vector2Pool.java) & [Vector3Pool](https://github.com/crykn/guacamole/blob/master/gdx/src/main/java/de/damios/guacamole/gdx/math/pool/Vector3Pool.java)
* [FPSCounter](https://github.com/crykn/guacamole/blob/master/gdx/src/main/java/de/damios/guacamole/gdx/utils/FPSCounter.java)
* [IntVector2](https://github.com/crykn/guacamole/blob/master/gdx/src/main/java/de/damios/guacamole/gdx/math/IntVector2.java): a vector for integer values
* [Logger](https://github.com/crykn/guacamole/blob/master/gdx/src/main/java/de/damios/guacamole/gdx/log/Logger.java) & [LoggerService](https://github.com/crykn/guacamole/blob/master/gdx/src/main/java/de/damios/guacamole/gdx/log/LoggerService.java): supports formatting; is super-sourced on GWT via [formic](https://github.com/tommyettinger/formic); use `LoggerService.getLogger(MyGdxGame.class)` to obtain a logger; calling, for instance, `logger.error("something went %s!", "wrong")` leads to the following console output: `[ERROR] [c.b.g.m.MyGdxGame]: something went wrong!`
* [NestableFrameBuffer](https://github.com/crykn/guacamole/blob/master/gdx/src/main/java/de/damios/guacamole/gdx/graphics/NestableFrameBuffer.java): a nestable framebuffer implementation, see [here](https://github.com/crykn/libgdx-screenmanager/wiki/Custom-FrameBuffer-implementation) for details
* [QuadMeshGenerator](https://github.com/crykn/guacamole/blob/master/gdx/src/main/java/de/damios/guacamole/gdx/graphics/QuadMeshGenerator.java): allows easily creating different quads; is useful when applying shaders
* [ReflectionUtils](https://github.com/crykn/guacamole/blob/master/gdx/src/main/java/de/damios/guacamole/gdx/reflection/ReflectionUtils.java): utility methods for dealing with libGDX's reflection
* [ShaderCompatibilityHelper](https://github.com/crykn/guacamole/blob/master/gdx/src/main/java/de/damios/guacamole/gdx/graphics/ShaderCompatibilityHelper.java): Allows converting GLSL shader code from version 120 to 150
* [ShaderProgramFactory](https://github.com/crykn/guacamole/blob/master/gdx/src/main/java/de/damios/guacamole/gdx/graphics/ShaderProgramFactory.java) & [ShaderPreconditions](https://github.com/crykn/guacamole/blob/master/gdx/src/main/java/de/damios/guacamole/gdx/graphics/ShaderProgramFactory.java#L107): Adds methods to easily create & compile a shader (`#fromString(String, String)`); automatically throws exceptions when the compilation fails; prepends can be ignored
* [Text](https://github.com/crykn/guacamole/blob/master/gdx/src/main/java/de/damios/guacamole/gdx/assets/Text.java) asset type
   
### gdx-desktop

**Desktop project:**

```
dependencies {
    implementation "com.github.crykn.guacamole:gdx:$guacamoleVersion" // guacamole-core is included
    
    implementation "com.github.crykn.guacamole:gdx-desktop:$guacamoleVersion"
}
```

**What is added:**

* [StartOnFirstThreadHelper](https://github.com/crykn/guacamole/blob/master/gdx-desktop/src/main/java/de/damios/guacamole/gdx/StartOnFirstThreadHelper.java): Starts a new JVM if the current one was not started with the `-XstartOnFirstThread` argument on macOS; this is only needed for the LWJGL 3 backend!
   

