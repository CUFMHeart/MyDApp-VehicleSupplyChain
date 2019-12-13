package org.fisco.bcos.asset.clientt;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Properties;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.fisco.bcos.asset.contract.Asset;
import org.fisco.bcos.asset.contract.Asset.RegisterEventEventResponse;
import org.fisco.bcos.asset.contract.Asset.TransferEventEventResponse;
import org.fisco.bcos.channel.client.Service;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.Keys;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import org.fisco.bcos.asset.contract.VehicleSupplyChain;


public class SupplyChainCtrl {
    //  code from AssetClient.java
	static Logger logger = LoggerFactory.getLogger(AssetClient.class);

    private Web3j web3j;
    
    private String username;

	private Credentials credentials;

	public Web3j getWeb3j() {
		return web3j;
	}

	public void setWeb3j(Web3j web3j) {
		this.web3j = web3j;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public void recordAssetAddr(String address) throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		prop.setProperty("address", address);
		final Resource contractResource = new ClassPathResource("contract.properties");
		FileOutputStream fileOutputStream = new FileOutputStream(contractResource.getFile());
		prop.store(fileOutputStream, "contract address");
	}

	public String loadAssetAddr() throws Exception {
		// load Asset contact address from contract.properties
		Properties prop = new Properties();
		final Resource contractResource = new ClassPathResource("contract.properties");
		prop.load(contractResource.getInputStream());

		String contractAddress = prop.getProperty("address");
		if (contractAddress == null || contractAddress.trim().equals("")) {
			throw new Exception(" load Asset contract address failed, please deploy it first. ");
		}
		logger.info(" load Asset address from contract.properties, address is {}", contractAddress);
		return contractAddress;
	}

	public void initialize() throws Exception {

		// init the Service
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		Service service = context.getBean(Service.class);
		service.run();

		ChannelEthereumService channelEthereumService = new ChannelEthereumService();
		channelEthereumService.setChannelService(service);
		Web3j web3j = Web3j.build(channelEthereumService, 1);

		// init Credentials
		Credentials credentials = Credentials.create(Keys.createEcKeyPair());

		setCredentials(credentials);
		setWeb3j(web3j);

		logger.debug(" web3j is " + web3j + " ,credentials is " + credentials);
	}

	private static BigInteger gasPrice = new BigInteger("30000000");
	private static BigInteger gasLimit = new BigInteger("30000000");

	public void deployAssetAndRecordAddr() {

		try {
			VehicleSupplyChain asset = VehicleSupplyChain.deploy(web3j, credentials, new StaticGasProvider(gasPrice, gasLimit)).send();
			System.out.println(" deploy Asset success, contract address is " + asset.getContractAddress());

			recordAssetAddr(asset.getContractAddress());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println(" deploy Asset contract failed, error message is  " + e.getMessage());
		}
	}

	public void queryAssetAmount(String assetAccount) {
		try {
			String contractAddress = loadAssetAddr();

			Asset asset = Asset.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
			Tuple2<BigInteger, BigInteger> result = asset.select(assetAccount).send();
			if (result.getValue1().compareTo(new BigInteger("0")) == 0) {
				System.out.printf(" asset account %s, value %s \n", assetAccount, result.getValue2());
			} else {
				System.out.printf(" %s asset account is not exist \n", assetAccount);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			logger.error(" queryAssetAmount exception, error message is {}", e.getMessage());

			System.out.printf(" query asset account failed, error message is %s\n", e.getMessage());
		}
	}

	public void registerAssetAccount(String assetAccount, BigInteger amount) {
		try {
			String contractAddress = loadAssetAddr();

			Asset asset = Asset.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = asset.register(assetAccount, amount).send();
			List<RegisterEventEventResponse> response = asset.getRegisterEventEvents(receipt);
			if (!response.isEmpty()) {
				if (response.get(0).ret.compareTo(new BigInteger("0")) == 0) {
					System.out.printf(" register asset account success => asset: %s, value: %s \n", assetAccount,
							amount);
				} else {
					System.out.printf(" register asset account failed, ret code is %s \n",
							response.get(0).ret.toString());
				}
			} else {
				System.out.println(" event log not found, maybe transaction not exec. ");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();

			logger.error(" registerAssetAccount exception, error message is {}", e.getMessage());
			System.out.printf(" register asset account failed, error message is %s\n", e.getMessage());
		}
	}

	public void transferAsset(String fromAssetAccount, String toAssetAccount, BigInteger amount) {
		try {
			String contractAddress = loadAssetAddr();
			Asset asset = Asset.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = asset.transfer(fromAssetAccount, toAssetAccount, amount).send();
			List<TransferEventEventResponse> response = asset.getTransferEventEvents(receipt);
			if (!response.isEmpty()) {
				if (response.get(0).ret.compareTo(new BigInteger("0")) == 0) {
					System.out.printf(" transfer success => from_asset: %s, to_asset: %s, amount: %s \n",
							fromAssetAccount, toAssetAccount, amount);
				} else {
					System.out.printf(" transfer asset account failed, ret code is %s \n",
							response.get(0).ret.toString());
				}
			} else {
				System.out.println(" event log not found, maybe transaction not exec. ");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();

			logger.error(" registerAssetAccount exception, error message is {}", e.getMessage());
			System.out.printf(" register asset account failed, error message is %s\n", e.getMessage());
		}
	}

	public static void Usage() {
		System.out.println(" Usage:");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.asset.client.AssetClient deploy");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.asset.client.AssetClient query account");
		System.out.println(
				"\t java -cp conf/:lib/*:apps/* org.fisco.bcos.asset.client.AssetClient register account value");
		System.out.println(
				"\t java -cp conf/:lib/*:apps/* org.fisco.bcos.asset.client.AssetClient transfer from_account to_account amount");
		System.exit(0);
    }
    
    public String formatInt(String str) {
        int result = 0;
        String tmp = "";
        boolean flag = true;
        int len = str.length();
        String hex_string = "0123456789abcdef";
        for (int i = len - 15; i < str.length(); i++) {
            if (flag) {
                if(str.charAt(i) != '0') {
                    flag = false;
                    tmp += str.charAt(i);
                }
            } else {
                tmp += str.charAt(i);
            }
        }
        for (int i = 0; i < tmp.length(); i++) {
            result *= 16;
            result += (hex_string.indexOf(tmp.charAt(i)));
        }
        return String.valueOf(result);
    }

	public String formatWord(String str) {
        int cnt = 0;
        String tmp = "";
        Boolean flag = true;
        String hex_string = "0123456789abcdef";
        if (str != "") {
            for(int i = 2; i < str.length(); i++) {
                if (flag) {
                    if(str.charAt(i)!='0') {
                        flag = false;
                        tmp += str.charAt(i);
                    }
                } else {
                    tmp += str.charAt(i);
                }
            }
            str = tmp;
        }
        char[] str_cArr = str.toCharArray();
        byte[] str_bytes = new byte[str.length() / 2];
		for (int i = 0; i < str_bytes.length; i++) {
			cnt = hex_string.indexOf(str_cArr[2 * i]) * 16;
			cnt += hex_string.indexOf(str_cArr[2 * i + 1]);
			str_bytes[i] = (byte) (cnt & 0xff);
		}
		return new String(str_bytes);
    }
    
    public void ctrlInit() {
        //  
		try {
            //  
            initialize();
            //  
            deployAssetAndRecordAddr();
			String contractAddr = loadAssetAddr();
            VehicleSupplyChain contr = VehicleSupplyChain.load(contractAddr, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            //  
            contr.registerCompany("CarCompany").send();
            contr.registerCompany("TireCompany").send();          
            contr.registerCompany("HubCompany").send();
		} catch (Exception e) {
			System.out.println("error: "+e.getMessage());
		}
    }

    public void loginCtrl(String company_name) {
        username = company_name;
    }

    public String getAssetCtrl() {
        //  
        try {
			String contractAddr = loadAssetAddr();
            VehicleSupplyChain contr = VehicleSupplyChain.load(contractAddr, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            //  
            TransactionReceipt result = contr.getCompanyAsset(username).send();
            return formatInt(result.getOutput());
		} catch (Exception e) {
			System.out.println("error: "+e.getMessage());
        }
        return "0";
    }

    public String getReceiptAmountCtrl(String to_company_name) {
        //  
        try {
			String contractAddr = loadAssetAddr();
            VehicleSupplyChain contr = VehicleSupplyChain.load(contractAddr, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            //  
            TransactionReceipt result = contr.getReceiptAmount(username, to_company_name).send();
            return formatInt(result.getOutput());
		} catch (Exception e) {
			System.out.println("error: "+e.getMessage());
        }
        return "0";
    }

    public void signReceiptCtrl(String from_company_name, int receipt_amount) {
        //  
        try {
			String contractAddr = loadAssetAddr();
            VehicleSupplyChain contr = VehicleSupplyChain.load(contractAddr, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            //  
            contr.signReceipt(username, from_company_name, BigInteger.valueOf(receipt_amount)).send();
		} catch (Exception e) {
			System.out.println("error: "+e.getMessage());
		}
    }

    public void transferReceiptCtrl(String from_company_name, String to_company_name, int receipt_amount) {
        //  
        try {
			String contractAddr = loadAssetAddr();
            VehicleSupplyChain contr = VehicleSupplyChain.load(contractAddr, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            //  
            contr.transferReceipt(username, from_company_name, to_company_name, BigInteger.valueOf(receipt_amount)).send();
		} catch (Exception e) {
			System.out.println("error: "+e.getMessage());
		}
    }

    public String financingWithReceiptCtrl(int amount) {
        //  
        try {
			String contractAddr = loadAssetAddr();
            VehicleSupplyChain contr = VehicleSupplyChain.load(contractAddr, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            //  
            TransactionReceipt result = contr.financingWithReceipt(username, BigInteger.valueOf(amount)).send();
            return formatWord(result.getOutput());
		} catch (Exception e) {
			System.out.println("error: "+e.getMessage());
        }
        return "false";
    }

    public String clearReceiptCtrl(String from_company_name) {
        //  
        try {
			String contractAddr = loadAssetAddr();
            VehicleSupplyChain contr = VehicleSupplyChain.load(contractAddr, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            //  
            TransactionReceipt result = contr.clearReceipt(username, from_company_name).send();
            return formatWord(result.getOutput());
		} catch (Exception e) {
			System.out.println("error: "+e.getMessage());
        }
        return "false";
    }
}