
# set the working directory to be the same as file path
dir <- dirname(rstudioapi::getActiveDocumentContext()$path)
setwd(dir)

MyData <- read.csv(file="results.csv", header=TRUE, sep=",")
MyData <- MyData[2:101,][3:47]

lables <- c("Cosine", "Jaccard", "RBF", "Ngram", "Needleman_wunch")

for (i in 1:5){
  http_sip <- as.double(MyData[,i])
  http_ftp <- as.double(MyData[,(5+i)])
  sip_ftp <- as.double(MyData[,(5*2+i)])
  http_http_5 <- as.double(MyData[1:50,(5*3+i)])
  sip_sip_5 <- as.double(MyData[1:50,(5*4+i)])
  ftp_ftp_5 <- as.double(MyData[1:50,(5*5+i)])
  http_http_1 <- as.double(MyData[1:50,(5*6+i)])
  sip_sip_1 <- as.double(MyData[1:50,(5*7+i)])
  ftp_ftp_1 <- as.double(MyData[1:50,(5*8+i)])

  par(mfrow = c(3,3))
  plot(http_sip, main=paste(lables[i], "HTTP/SIP", sep=" "), sub=paste("η =", mean(http_sip), ", σ =",sd(http_sip), sep=" "))
  plot(http_ftp, main=paste(lables[i], "HTTP/FTP", sep=" "), sub=paste("η =", mean(http_ftp), ", σ =",sd(http_ftp), sep=" "))
  plot(sip_ftp, main=paste(lables[i], "SIP/FTP", sep=" "), sub=paste("η =", mean(sip_ftp), ", σ =",sd(sip_ftp), sep=" "))
  plot(http_http_5, main=paste(lables[i], "HTTP/HTTP (50% DIF)", sep=" "), sub=paste("η =", mean(http_http_5), ", σ =",sd(http_http_5), sep=" "))
  plot(ftp_ftp_5, main=paste(lables[i], "FTP/FTP (50% DIF)", sep=" "), sub=paste("η =", mean(ftp_ftp_5), ", σ =",sd(ftp_ftp_5), sep=" "))
  plot(sip_sip_5 , main=paste(lables[i], "SIP/SIP (50% DIF)", sep=" "), sub=paste("η =", mean(sip_sip_5), ", σ =",sd(sip_sip_5), sep=" "))
  plot(http_http_1, main=paste(lables[i], "HTTP/HTTP (100% DIF)", sep=" "), sub=paste("η =", mean(http_http_1), ", σ =",sd(http_http_1), sep=" "))
  plot(ftp_ftp_1, main=paste(lables[i], "FTP/FTP (100% DIF)", sep=" "), sub=paste("η =", mean(ftp_ftp_1), ", σ =",sd(ftp_ftp_1), sep=" "))
  plot(sip_sip_1, main=paste(lables[i], "SIP/SIP (100% DIF)", sep=" "), sub=paste("η =", mean(sip_sip_1), ", σ =",sd(sip_sip_1), sep=" "))
}
