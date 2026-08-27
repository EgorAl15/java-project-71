.PHONY: build run-dist

ifeq ($(OS),Windows_NT)
RUN_DIST = .\build\install\app\bin\app.bat
GRADLE = .\gradlew.bat
else
RUN_DIST = ./build/install/app/bin/app
GRADLE = ./gradlew
endif

build:
	$(GRADLE) build

run-dist:
	$(RUN_DIST)
