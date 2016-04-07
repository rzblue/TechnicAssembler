#!/bin/bash
cat > ~/.gradle/gradle.properties << "EOF"
signing.keyId=
signing.password=
signing.secretKeyRingFile=

sonatypeUsername=
sonatypePassword=
EOF

