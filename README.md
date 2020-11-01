 guacamole

[![Release](https://jitpack.io/v/crykn/guacamole.svg)](https://jitpack.io/#crykn/guacamole) ![GWT Compatible](https://img.shields.io/badge/GWT-compatible-informational)

A small collection of some common and **very basic utilities for [libGDX](https://github.com/libgdx/libgdx) games**, which is used in [libgdx-screenmanager](https://github.com/crykn/libgdx-screenmanager) and [pancake](https://github.com/eskalon/pancake).

## Core 

```
dependencies {
    implementation 'com.github.crykn.guacamole:core:$guacamoleVersion'
}
```

* [Preconditions](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/Preconditions.java): has methods like `#checkArgument(boolean, String)`, `#checkState(boolean, String)` & `#checkNotNull(Object, String)`; is slimmer than the commonly known class from [guava](https://github.com/google/guava/wiki/PreconditionsExplained)
* [@Nullable](https://jcp.org/en/jsr/detail?id=305#2): the `@Nullable` annotation from JSR 305 is provided as a dependency 
* [Exceptions](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/Exceptions.java): `#throwAsRuntimeException(Exception)`
* [Pair](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/tuple/Pair.java) & [Triple](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/tuple/Triple.java) implementations (immutable)
* [@Beta](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/annotations/Beta.java): an annotation to denote APIs that may be subject to changes in future releases
* [@GwtIncompatible](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/annotations/GwtIncompatible.java): indicates that an API is not compatible with GWT and thus shouldn't be compiled for it
* Simple callback & listener interfaces: [ISimpleCallback](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/ISimpleCallback.java), [ISimpleListener](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/ISimpleListener.java), [ISuccessCallback](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/ISuccessCallback.java)
* [ThreadHandler](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/concurrent/ThreadHandler.java): a class to easily offload simple, time-consuming tasks onto threads
* [DaemonThreadFactory](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/concurrent/DaemonThreadFactory.java): a `ThreadFactory` creating daemon threads

<br/>

## gdx & gdx-desktop & gdx-gwt

### gdx & gdx-gwt

**Core project:**

```
dependencies {
    // guacamole-core is included
    implementation "com.github.crykn.guacamole:gdx:$guacamoleVersion"
}
```

**Html project:**

```
dependencies {
    implementation project(":core")
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

   * [NestableFrameBuffer](https://github.com/crykn/guacamole/blob/master/gdx/src/main/java/de/damios/guacamole/gdx/graphics/NestableFrameBuffer.java): a nestable framebuffer implementation, see [here](https://github.com/crykn/libgdx-screenmanager/wiki/Custom-FrameBuffer-implementation) for details
   * [ShaderProgramFactory](https://github.com/crykn/guacamole/blob/master/gdx/src/main/java/de/damios/guacamole/gdx/graphics/ShaderProgramFactory.java) & [ShaderPreconditions](https://github.com/crykn/guacamole/blob/master/gdx/src/main/java/de/damios/guacamole/gdx/graphics/ShaderProgramFactory.java#L107): Adds methods to easily create & compile a shader (`#fromString(String, String)`); automatically throws exceptions when the compilation fails; prepends can be ignored
   * [Log](https://github.com/crykn/guacamole/blob/master/gdx/src/main/java/de/damios/guacamole/gdx/Log.java): Adds a static helper class to log stuff using the libGDX logger; adds support for `String#format(String, Object...)`; is super-sourced on GWT via [formic](https://github.com/tommyettinger/formic)
   * [Text](https://github.com/crykn/guacamole/blob/master/gdx/src/main/java/de/damios/guacamole/gdx/assets/Text.java) asset type
   * [QuadMeshGenerator](https://github.com/crykn/guacamole/blob/master/gdx/src/main/java/de/damios/guacamole/gdx/graphics/QuadMeshGenerator.java): allows easily creating different quads; is useful when applying shaders
   * [DefaultInputProcessor](https://github.com/crykn/guacamole/blob/master/gdx/src/main/java/de/damios/guacamole/gdx/DefaultInputProcessor.java): an `InputProcessor` providing default methods to reduce boilerplate code; is an interface alternative to `InputAdapter`
   
### gdx-desktop

**Desktop project:**

```
dependencies {
    implementation project(":core")
    implementation "com.github.crykn.guacamole:gdx-desktop:$guacamoleVersion"
}
```

**What is added:**

   * [StartOnFirstThreadHelper](https://github.com/crykn/guacamole/blob/master/gdx-desktop/src/main/java/de/damios/guacamole/gdx/StartOnFirstThreadHelper.java): Starts a new JVM if the current one was not started with the `-XstartOnFirstThread` argument on Mac OS
   

