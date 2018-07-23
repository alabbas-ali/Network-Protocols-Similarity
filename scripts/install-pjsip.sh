#!/bin/bash -v
export HOME=/root

### @export "capture-logs"
exec > >(tee /var/log/user-data.log|logger -t user-data -s 2>/dev/console) 2>&1

### @export "get-release-name"
source /etc/lsb-release
echo $DISTRIB_CODENAME
echo "deb http://us-east-1.ec2.archive.ubuntu.com/ubuntu $DISTRIB_CODENAME multiverse" >> /etc/apt/sources.list

### @export "update-package-manager"
apt-get update
apt-get upgrade -y --force-yes

### @export "ubuntu-server-installs"
apt-get install -y build-essential
apt-get install -y autoconf
apt-get install -y checkinstall
apt-get install -y git
apt-get install -y mercurial
apt-get install -y libfaac-dev
apt-get install -y libgpac-dev
apt-get install -y libmp3lame-dev
apt-get install -y libopencore-amrnb-dev
apt-get install -y libopencore-amrwb-dev
apt-get install -y libtheora-dev
apt-get install -y libvorbis-dev
apt-get install -y texi2html # why?
apt-get install -y yasm
apt-get install -y zlib1g-dev
apt-get install -y libsdl-sound1.2-dev
apt-get install -y libportaudio-dev

# Install ffmpeg as per https://ffmpeg.org/trac/ffmpeg/wiki/UbuntuCompilationGuide

### @export "install-x264"
cd
git clone git://git.videolan.org/x264
cd x264
./configure --enable-static
make
checkinstall --pkgname=x264 --pkgversion="3:$(./version.sh | \
      awk -F'[" ]' '/POINT/{print $4"+git"$5}')" --backup=no --deldoc=yes \
        --fstrans=no --default

### @export "install-libvpx"
cd
git clone http://git.chromium.org/webm/libvpx.git
cd libvpx
./configure
make
checkinstall --pkgname=libvpx --pkgversion="1:$(date +%Y%m%d%H%M)-git" --backup=no \
      --deldoc=yes --fstrans=no --default

### @export "install-ffmpeg-source"
cd
git clone --depth 1 git://source.ffmpeg.org/ffmpeg
cd ffmpeg
./configure --enable-gpl --enable-libfaac --enable-libmp3lame --enable-libopencore-amrnb \
      --enable-libopencore-amrwb --enable-libtheora --enable-libvorbis --enable-libvpx \
        --enable-libx264 --enable-nonfree --enable-version3
make
checkinstall --pkgname=ffmpeg --pkgversion="5:$(date +%Y%m%d%H%M)-git" --backup=no \
      --deldoc=yes --fstrans=no --default

hash x264 ffmpeg ffplay ffprobe

### @export "install-pjsip"
wget http://www.pjsip.org/release/1.12/pjproject-1.12.tar.bz2
tar -xjf pjproject-1.12.tar.bz2
cd pjproject-1.12/
./configure
make dep && make && make install
cp pjsip-apps/bin/pjsua* /usr/local/bin/pjsua
cd ..
