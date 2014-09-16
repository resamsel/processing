GIFS = $(shell echo bin/*-*.png | sed 's/.png/.gif/g')
SOURCES = $(wildcard bin/*-*.png)
INTERMEDIATE = $(SOURCES:.png=.gif)

bin/%.gif: bin/%.png
	convert $^ $@

target/triangle.gif: $(patsubst %.png,%.gif,$(wildcard bin/triangle-*.png))
	mkdir -p target
	gifsicle --loopcount -i --delay 4 $^ > $@

target/starrotation.gif: $(patsubst %.png,%.gif,$(wildcard bin/starrotation-*.png))
	mkdir -p target
	gifsicle --loopcount -i --delay 4 $^ > $@

target/starpattern.gif: $(patsubst %.png,%.gif,$(wildcard bin/starpattern-*.png))
	mkdir -p target
	gifsicle --loopcount -i --delay 4 $^ > $@

target/bluedots.gif: $(patsubst %.png,%.gif,$(wildcard bin/bluedots-*.png))
	mkdir -p target
	gifsicle --loopcount -i --delay 4 $^ > $@

target/friendshipbracelet.gif: $(patsubst %.png,%.gif,$(wildcard bin/friendshipbracelet-*.png))
	mkdir -p target
	gifsicle --loopcount -i --delay 2 --colors 256 $^ > $@

clean:
	rm -f bin/image-*.png
	rm -f bin/image-*.gif

