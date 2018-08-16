
# set the working directory to be the same as file path
dir <- dirname(rstudioapi::getActiveDocumentContext()$path)
setwd(dir)

MyData <- read.csv(file="results.csv", header=TRUE, sep=",")
MyData <- MyData[2:101,][3:47]

http_sip_cosine <- MyData[,1]
http_ftp_cosine <- MyData[,6]
sip_ftp_cosine <- MyData[,11]
http_http_cosine_5 <- MyData[1:50,16]
sip_sip_cosine_5 <- MyData[1:50,21]
ftp_ftp_cosine_5 <- MyData[1:50,26]
http_http_cosine_1 <- MyData[1:50,31]
sip_sip_cosine_1 <- MyData[1:50,36]
ftp_ftp_cosine_1 <- MyData[1:50,41]
par(mfrow = c(1,3))
plot(http_sip_cosine, main="HTTP/SIP")
plot(http_ftp_cosine, main="HTTP/FTP")
plot(sip_ftp_cosine, main="SIP/FTP")
par(mfrow = c(1,3))
plot(http_http_cosine_5, main="HTTP/HTTP (50% DIF)")
plot(ftp_ftp_cosine_5, main="FTP/FTP (50% DIF)")
plot(sip_sip_cosine_5 , main="SIP/SIP (50% DIF)")
par(mfrow = c(1,3))
plot(http_http_cosine_1, main="HTTP/HTTP (100% DIF)")
plot(ftp_ftp_cosine_1, main="FTP/FTP (100% DIF)")
plot(sip_sip_cosine_1, main="SIP/SIP (100% DIF)")



http_sip_Jaccard <- MyData[,2]
http_ftp_Jaccard <- MyData[,7]
sip_ftp_Jaccard <- MyData[,12]
http_http_Jaccard_5 <- MyData[1:50,17]
sip_sip_Jaccard_5 <- MyData[1:50,22]
ftp_ftp_Jaccard_5 <- MyData[1:50,27]
http_http_Jaccard_1 <- MyData[1:50,32]
sip_sip_Jaccard_1 <- MyData[1:50,37]
ftp_ftp_Jaccard_1 <- MyData[1:50,42]

par(mfrow = c(3,3))
plot(http_sip_Jaccard, xlabel="HTTP/SIP")
plot(http_ftp_Jaccard, xlabel="HTTP/FTP")
plot(sip_ftp_Jaccard, xlabel="SIP/FTP")
plot(http_http_Jaccard_5, xlabel="HTTP/HTTP (50% DIF)")
plot(http_http_Jaccard_1, xlabel="HTTP/HTTP (100% DIF)")
plot(sip_sip_Jaccard_5 , xlabel="SIP/SIP (50% DIF)")
plot(sip_sip_Jaccard_1, xlabel="SIP/SIP (100% DIF)")
plot(ftp_ftp_Jaccard_5, xlabel="FTP/FTP (50% DIF)")
plot(ftp_ftp_Jaccard_1, xlabel="FTP/FTP (100% DIF)")

http_sip_RBF <- MyData[,3]
http_ftp_RBF <- MyData[,8]
sip_ftp_RBF <- MyData[,13]
http_http_RBF_5 <- MyData[1:50,18]
sip_sip_RBF_5 <- MyData[1:50,23]
ftp_ftp_RBF_5 <- MyData[1:50,28]
http_http_RBF_1 <- MyData[1:50,33]
sip_sip_RBF_1 <- MyData[1:50,38]
ftp_ftp_RBF_1 <- MyData[1:50,43]

par(mfrow = c(3,3))
plot(http_sip_RBF, xlabel="HTTP/SIP")
plot(http_ftp_RBF, xlabel="HTTP/FTP")
plot(sip_ftp_RBF, xlabel="SIP/FTP")
plot(http_http_RBF_5, xlabel="HTTP/HTTP (50% DIF)")
plot(http_http_RBF_1, xlabel="HTTP/HTTP (100% DIF)")
plot(sip_sip_RBF_5 , xlabel="SIP/SIP (50% DIF)")
plot(sip_sip_RBF_1, xlabel="SIP/SIP (100% DIF)")
plot(ftp_ftp_RBF_5, xlabel="FTP/FTP (50% DIF)")
plot(ftp_ftp_RBF_1, xlabel="FTP/FTP (100% DIF)")

http_sip_Ngram <- MyData[,4]
http_ftp_Ngram <- MyData[,9]
sip_ftp_Ngram <- MyData[,14]
http_http_Ngram_5 <- MyData[1:50,19]
sip_sip_Ngram_5 <- MyData[1:50,24]
ftp_ftp_Ngram_5 <- MyData[1:50,29]
http_http_Ngram_1 <- MyData[1:50,34]
sip_sip_Ngram_1 <- MyData[1:50,39]
ftp_ftp_Ngram_1 <- MyData[1:50,44]

par(mfrow = c(3,3))
plot(http_sip_Ngram, xlabel="HTTP/SIP")
plot(http_ftp_Ngram, xlabel="HTTP/FTP")
plot(sip_ftp_Ngram, xlabel="SIP/FTP")
plot(http_http_Ngram_5, xlabel="HTTP/HTTP (50% DIF)")
plot(http_http_Ngram_1, xlabel="HTTP/HTTP (100% DIF)")
plot(sip_sip_Ngram_5 , xlabel="SIP/SIP (50% DIF)")
plot(sip_sip_Ngram_1, xlabel="SIP/SIP (100% DIF)")
plot(ftp_ftp_Ngram_5, xlabel="FTP/FTP (50% DIF)")
plot(ftp_ftp_Ngram_1, xlabel="FTP/FTP (100% DIF)")

http_sip_Needleman_wunch <- MyData[,5]
http_ftp_Needleman_wunch <- MyData[,10]
sip_ftp_Needleman_wunch <- MyData[,15]
http_http_Needleman_wunch_5 <- MyData[1:50,20]
sip_sip_Needleman_wunch_5 <- MyData[1:50,25]
ftp_ftp_Needleman_wunch_5 <- MyData[1:50,30]
http_http_Needleman_wunch_1 <- MyData[1:50,35]
sip_sip_Needleman_wunch_1 <- MyData[1:50,40]
ftp_ftp_Needleman_wunch_1 <- MyData[1:50,45]

par(mfrow = c(3,3))
plot(http_sip_Needleman_wunch, xlabel="HTTP/SIP")
plot(http_ftp_Needleman_wunch, xlabel="HTTP/FTP")
plot(sip_ftp_Needleman_wunch, xlabel="SIP/FTP")
plot(http_http_Needleman_wunch_5, xlabel="HTTP/HTTP (50% DIF)")
plot(http_http_Needleman_wunch_1, xlabel="HTTP/HTTP (100% DIF)")
plot(sip_sip_Needleman_wunch_5 , xlabel="SIP/SIP (50% DIF)")
plot(sip_sip_Needleman_wunch_1, xlabel="SIP/SIP (100% DIF)")
plot(ftp_ftp_Needleman_wunch_5, xlabel="FTP/FTP (50% DIF)")
plot(ftp_ftp_Needleman_wunch_1, xlabel="FTP/FTP (100% DIF)")
