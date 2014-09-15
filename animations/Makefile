GIFS = $(shell echo bin/image-*.png | sed 's/.png/.gif/g')

bin/image-%.gif: bin/image-%.png
	convert $^ $@

target/triangle.gif: $(GIFS)
	mkdir -p target
	gifsicle --loopcount -i --delay 4 $^ > $@

target/bluedots.gif: $(GIFS)
	mkdir -p target
	gifsicle --loopcount -i --delay 4 $^ > $@

target/starrotation.gif: $(GIFS)
	mkdir -p target
	gifsicle --loopcount -i --delay 4 $^ > $@

target/starpattern.gif: $(GIFS)
	mkdir -p target
	gifsicle --loopcount -i --delay 4 $^ > $@

clean:
	rm -f bin/image-*.png
	rm -f bin/image-*.gif

