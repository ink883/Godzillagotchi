#!/bin/bash
set -euo pipefail
cd "$(dirname "$0")"
JDK="${JDK:-/Applications/Kindle Previewer 3.app/Contents/lib/fc/jre}"
BT="${BT:-/private/tmp/rg-rotate-android-tools/android-14}"
ANDROID_JAR="${ANDROID_JAR:-/private/tmp/rg-rotate-android-platform/android-12/android.jar}"
export JAVA_HOME="$JDK"
export PATH="$JDK/bin:$PATH"
mkdir -p build/classes build/gen build/dex .signing
"$BT/aapt" package -f -m -M AndroidManifest.xml -S res -I "$ANDROID_JAR" -J build/gen -F build/resources.apk
"$JDK/bin/javac" -source 8 -target 8 -bootclasspath "$ANDROID_JAR" -d build/classes src/com/nova/godzillagotchi/*.java build/gen/com/nova/godzillagotchi/R.java
"$JDK/bin/jar" cf build/classes.jar -C build/classes .
"$JDK/bin/java" -cp "$BT/lib/d8.jar" com.android.tools.r8.D8 --release --min-api 26 --lib "$ANDROID_JAR" --output build/dex build/classes.jar
cp build/resources.apk build/unsigned.apk
(cd build/dex && zip -q ../unsigned.apk classes.dex)
"$BT/zipalign" -f -p 4 build/unsigned.apk build/aligned.apk
if [ ! -f .signing/personal.jks ]; then
 "$JDK/bin/keytool" -genkeypair -keystore .signing/personal.jks -storepass android -keypass android -alias personal -keyalg RSA -keysize 2048 -validity 10000 -dname 'CN=Nova Personal Build'
fi
"$BT/apksigner" sign --ks .signing/personal.jks --ks-pass pass:android --out ../Godzillagotchi-3.5.apk build/aligned.apk
"$BT/apksigner" verify --verbose ../Godzillagotchi-3.5.apk
"$BT/aapt" dump badging ../Godzillagotchi-3.5.apk
