# guacamole

A small library of some common and **very basic utils for [libGDX](https://github.com/libgdx/libgdx) games**, which is used in [libgdx-screenmanager](https://github.com/crykn/libgdx-screenmanager) and [eskalon-commons](https://github.com/eskalon/eskalon-commons).

## Core 

```
dependencies {
    implementation 'com.github.crykn.guacamole:core:X.Y.Z'
}
```

* [`Preconditions`](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/Preconditions.java): has methods like `#checkArgument(boolean, String)`, `#checkState(boolean, String)` & `checkNotNull(Object, String)`; is slimmer than the commonly known class from [guava](https://github.com/google/guava/wiki/PreconditionsExplained)
* [`Exceptions`](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/Exceptions.java): `#throwAsRuntimeException(Exception)`
* [Pair](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/tuple/Pair.java) & [Triple](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/tuple/Triple.java) implementations (immutable)
* [`@Beta`](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/annotations/Beta.java): an annotation to denote APIs that may be subject to changes in future releases
* [`@GwtIncompatible`](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/annotations/GwtIncompatible.java): indicates that an API is not compatible with GWT and thus shouldn't be compiled for it
* Simple callback & listener interfaces: [`ISimpleCallback`](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/ISimpleCallback.java), [`ISimpleListener`](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/ISimpleListener.java), [`ISuccessCallback`](https://github.com/crykn/guacamole/blob/master/core/src/main/java/de/damios/guacamole/ISuccessCallback.java)

## gdx & gdx-desktop & gdx-gwt

**Core project:**

```
dependencies {
    implementation 'com.github.crykn.guacamole:gdx:X.Y.Z'
}
```

**Desktop project:**

```
dependencies {
    implementation 'com.github.crykn.guacamole:gdx:X.Y.Z'
    implementation 'com.github.crykn.guacamole:gdx-desktop:X.Y.Z'
}
```

**Html project:**

```
dependencies {
    implementation 'com.github.crykn.guacamole:gdx:X.Y.Z:sources'
    implementation 'com.github.crykn.guacamole:gdx-gwt:X.Y.Z:sources'
}
```

Module file (_GdxDefinition.gwt.xml_):

```
<inherits name="guacamole_gdx" />	
<inherits name="guacamole_gdx_gwt" />
```

### What is added:

* [`NestableFrameBuffer`](https://github.com/crykn/guacamole/blob/master/gdx/src/main/java/de/damios/guacamole/gdx/NestableFrameBuffer.java): a nestable frame buffer implementation, see [here](https://github.com/crykn/libgdx-screenmanager/wiki/Custom-FrameBuffer-implementation) for details
   * `GLUtils` (super-sourced in guacamole-gdx-gwt):  adds a `#getBoundFboHandle()` method
* [`Sync`](https://github.com/crykn/guacamole/blob/master/gdx-desktop/src/main/java/de/damios/guacamole/gdx/Sync.java) (in guacamole-gdx-desktop): Adds the commonly used [sync class](http://forum.lwjgl.org/index.php?topic=6582.msg34846#msg34846) from LWJGL2; see [here](https://github.com/crykn/guacamole/wiki/Sync-usage) for how to use it
* [`ShaderProgramFactory`](https://github.com/crykn/guacamole/blob/master/gdx/src/main/java/de/damios/guacamole/gdx/ShaderProgramFactory.java) & [`ShaderPreconditions`](https://github.com/crykn/guacamole/blob/master/gdx/src/main/java/de/damios/guacamole/gdx/ShaderProgramFactory.java#L107): Adds methods to easily create & compile a shader; exceptions can be automatically thrown when the compilation fails; prepends can be ignored
* [`Log`](https://github.com/crykn/guacamole/blob/master/gdx/src/main/java/de/damios/guacamole/gdx/Log.java): Adds a static helper class to log stuff using the libGDX logger; adds support for `String#format(String, Object...)`; does also work on GWT via [formic](https://github.com/tommyettinger/formic)
* [`MeshGenerator`](https://github.com/crykn/guacamole/blob/master/gdx/src/main/java/de/damios/guacamole/gdx/MeshGenerator.java): allows easily creating different quads

