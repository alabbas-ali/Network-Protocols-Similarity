
# set the working directory to be the same as file path
dir <- dirname(rstudioapi::getActiveDocumentContext()$path)
setwd(dir)

MyData <- read.csv(file="results.csv", header=TRUE, sep=",")
MyData <- data.frame(MyData[2:101,][3:47])

as.numeric.factor <- function(x) {as.numeric(levels(x))[x]}

lables <- c("Cosine", "Jaccard", "RBF", "Ngram", "Needleman Wunch")

for (i in 1:5) {
  http_sip <- as.numeric.factor(MyData[,i])
  http_ftp <- as.numeric.factor(MyData[,(5+i)])
  sip_ftp <- as.numeric.factor(MyData[,(5*2+i)])
  http_http_5 <- as.numeric.factor(MyData[1:50,(5*3+i)])
  sip_sip_5 <- as.numeric.factor(MyData[1:50,(5*4+i)])
  ftp_ftp_5 <- as.numeric.factor(MyData[1:50,(5*5+i)])
  http_http_1 <- as.numeric.factor(MyData[1:50,(5*6+i)])
  sip_sip_1 <- as.numeric.factor(MyData[1:50,(5*7+i)])
  ftp_ftp_1 <- as.numeric.factor(MyData[1:50,(5*8+i)])
  
  par(mfrow = c(3,3))
  plot(
    http_sip, 
    main=paste(lables[i], "HTTP/SIP", sep=" "), 
    sub=paste("µ =", mean(http_sip), ", σ =",sd(http_sip), sep=" "), 
    ylim = c(0, 1), 
    ylab = "Similarity Value", 
    xlab = "Sample Number"
  )
  plot(
    http_ftp, 
    main=paste(lables[i], "HTTP/FTP", sep=" "), 
    sub=paste("µ =", mean(http_ftp), ", σ =",sd(http_ftp), sep=" "), 
    ylim = c(0, 1),
    ylab = "Similarity Value", 
    xlab = "Sample Number"
  )
  plot(
    sip_ftp, 
    main=paste(lables[i], "SIP/FTP", sep=" "), 
    sub=paste("µ =", mean(sip_ftp), ", σ =",sd(sip_ftp), sep=" "), 
    ylim = c(0, 1),
    xlab = "Similarity Value", 
    ylab = "Sample Number"
  )
  plot(
    http_http_5, 
    main=paste(lables[i], "HTTP/HTTP (50% DIF)", sep=" "), 
    sub=paste("µ =", mean(http_http_5), ", σ =",sd(http_http_5), sep=" "), 
    ylim = c(0, 1),
    ylab = "Similarity Value", 
    xlab = "Sample Number"
  )
  plot(
    ftp_ftp_5, 
    main=paste(lables[i], "FTP/FTP (50% DIF)", sep=" "), 
    sub=paste("µ =", mean(ftp_ftp_5), ", σ =",sd(ftp_ftp_5), sep=" "), 
    ylim = c(0, 1),
    xlab = "Similarity Value", 
    ylab = "Sample Number"
  )
  plot(
    sip_sip_5 , 
    main=paste(lables[i], "SIP/SIP (50% DIF)", sep=" "), 
    sub=paste("µ =", mean(sip_sip_5), ", σ =",sd(sip_sip_5), sep=" "), 
    ylim = c(0, 1),
    ylab = "Similarity Value", 
    xlab = "Sample Number"
  )
  plot(
    http_http_1, 
    main=paste(lables[i], "HTTP/HTTP (100% DIF)", sep=" "), 
    sub=paste("µ =", mean(http_http_1), ", σ =",sd(http_http_1), sep=" "), 
    ylim = c(0, 1),
    ylab = "Similarity Value", 
    xlab = "Sample Number"
  )
  plot(
    ftp_ftp_1, 
    main=paste(lables[i], "FTP/FTP (100% DIF)", sep=" "), 
    sub=paste("µ =", mean(ftp_ftp_1), ", σ =",sd(ftp_ftp_1), sep=" "), 
    ylim = c(0, 1),
    ylab = "Similarity Value", 
    xlab = "Sample Number"
  )
  plot(
    sip_sip_1, 
    main= paste(lables[i], "SIP/SIP (100% DIF)", sep=" "), 
    sub=paste("µ =", mean(sip_sip_1), ", σ =",sd(sip_sip_1), sep=" "), 
    ylim = c(0, 1),
    ylab = "Similarity Value", 
    xlab = "Sample Number"
  )
  
  tt <- data.frame(
    replace(http_sip,  http_sip < 0 ,0),
    replace(http_ftp, http_ftp < 0 ,0), 
    replace(sip_ftp, sip_ftp < 0 ,0), 
    replace(http_http_5, http_http_5 < 0 ,0), 
    replace(ftp_ftp_5, ftp_ftp_5 < 0 ,0), 
    replace(sip_sip_5, sip_sip_5 < 0 ,0), 
    replace(http_http_1, http_http_1 < 0 ,0), 
    replace(ftp_ftp_1, ftp_ftp_1 < 0 ,0), 
    replace(sip_sip_1, sip_sip_1 < 0 ,0)
  )
  
  colnames(tt) <-  c(
    "HTTP/SIP",
    "HTTP/FTP",
    "SIP/FTP",
    "HTTP/HTTP 50%",
    "FTP/FTP 50%",
    "SIP/SIP 50%",
    "HTTP/HTTP 100%",
    "FTP/FTP 100%",
    "SIP/SIP 100%"
  )
  
  par(mfrow = c(1,1))
  boxplot(
    tt,
    main = paste(lables[i], "Boxplot" , sep=" "),
    col = 1:9
  )
  
}

