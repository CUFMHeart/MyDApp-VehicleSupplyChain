package org.com.fisco;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.Bool;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.abi.datatypes.generated.Int256;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint8;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple8;
import org.fisco.bcos.web3j.tx.Contract;
import org.fisco.bcos.web3j.tx.TransactionManager;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.fisco.bcos.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version none.
 */
@SuppressWarnings("unchecked")
public class VehicleSupplyChain extends Contract {
    public static String BINARY = "6080604052600160005560806040519081016040528073ed23e54c2c913a6f2baf0142da49cd535cb03d7a73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001730637e97d8bc4c51bef305e4426ebc6fbdcb8ac8173ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200173cf615d9d5cc285cffb62008bdf7b8a26495f120073ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020017355bb5204fd3e78b2b8341c5b9ca8228bd4f9df0a73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681525060049060046200013d929190620008ac565b503480156200014b57600080fd5b506000600460006004811015156200015f57fe5b0160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905060016008600001819055506040805190810160405280600481526020017f62616e6b0000000000000000000000000000000000000000000000000000000081525060086001019080519060200190620001de9291906200092e565b5080600860020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550620f424060086003018190555060006008600401819055506000600860050181905550600060038111156200025457fe5b600860060160006101000a81548160ff021916908360038111156200027557fe5b02179055506001600860060160016101000a81548160ff021916908315150217905550600860026000600181526020019081526020016000206000820154816000015560018201816001019080546001816001161561010002031660029004620002e1929190620009b5565b506002820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168160020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506003820154816003015560048201548160040155600582015481600501556006820160009054906101000a900460ff168160060160006101000a81548160ff021916908360038111156200039957fe5b02179055506006820160019054906101000a900460ff168160060160016101000a81548160ff0219169083151502179055509050506008600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082015481600001556001820181600101908054600181600116156101000203166002900462000442929190620009b5565b506002820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168160020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506003820154816003015560048201548160040155600582015481600501556006820160009054906101000a900460ff168160060160006101000a81548160ff02191690836003811115620004fa57fe5b02179055506006820160019054906101000a900460ff168160060160016101000a81548160ff021916908315150217905550905050600460006004811015156200054057fe5b0160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1660036000620005b56040805190810160405280600481526020017f62616e6b000000000000000000000000000000000000000000000000000000008152506200089e640100000000026401000000009004565b6000191660001916815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600460016004811015156200061a57fe5b0160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16600360006200068f6040805190810160405280600a81526020017f436172436f6d70616e79000000000000000000000000000000000000000000008152506200089e640100000000026401000000009004565b6000191660001916815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060046002600481101515620006f457fe5b0160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1660036000620007696040805190810160405280600b81526020017f54697265436f6d70616e790000000000000000000000000000000000000000008152506200089e640100000000026401000000009004565b6000191660001916815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060046003600481101515620007ce57fe5b0160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1660036000620008436040805190810160405280600a81526020017f487562436f6d70616e79000000000000000000000000000000000000000000008152506200089e640100000000026401000000009004565b6000191660001916815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505062000ab1565b600060208201519050919050565b82600481019282156200091b579160200282015b828111156200091a5782518260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555091602001919060010190620008c0565b5b5090506200092a919062000a43565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200097157805160ff1916838001178555620009a2565b82800160010185558215620009a2579182015b82811115620009a157825182559160200191906001019062000984565b5b509050620009b1919062000a89565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620009f0578054855562000a30565b8280016001018555821562000a3057600052602060002091601f016020900482015b8281111562000a2f57825482559160010191906001019062000a12565b5b50905062000a3f919062000a89565b5090565b62000a8691905b8082111562000a8257600081816101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690555060010162000a4a565b5090565b90565b62000aae91905b8082111562000aaa57600081600090555060010162000a90565b5090565b90565b6127158062000ac16000396000f3006080604052600436106100af576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063389d44a6146100b45780633a07637a146101b3578063505f26e61461027657806350e36e13146102f357806353391ada146104085780635a2b9d6414610479578063a47da8eb146104ec578063c046f81e146105a5578063cfb5192814610668578063d9308fb0146106ed578063ea72358414610818575b600080fd5b3480156100c057600080fd5b506101b1600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192908035906020019092919050505061089f565b005b3480156101bf57600080fd5b50610260600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506112a5565b6040518082815260200191505060405180910390f35b34801561028257600080fd5b506102dd600480360381019080803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506113c3565b6040518082815260200191505060405180910390f35b3480156102ff57600080fd5b5061031e60048036038101908080359060200190929190505050611457565b60405180898152602001806020018873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200187815260200186815260200185815260200184600381111561037c57fe5b60ff16815260200183151515158152602001828103825289818151815260200191508051906020019080838360005b838110156103c65780820151818401526020810190506103ab565b50505050905090810190601f1680156103f35780820380516001836020036101000a031916815260200191505b50995050505050505050505060405180910390f35b34801561041457600080fd5b506104376004803603810190808035600019169060200190929190505050611571565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561048557600080fd5b506104ea600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001909291905050506115a4565b005b3480156104f857600080fd5b506105a3600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001909291905050506118c9565b005b3480156105b157600080fd5b50610652600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050611cbc565b6040518082815260200191505060405180910390f35b34801561067457600080fd5b506106cf600480360381019080803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929050505061212f565b60405180826000191660001916815260200191505060405180910390f35b3480156106f957600080fd5b5061072e600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061213d565b60405180898152602001806020018873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200187815260200186815260200185815260200184600381111561078c57fe5b60ff16815260200183151515158152602001828103825289818151815260200191508051906020019080838360005b838110156107d65780820151818401526020810190506107bb565b50505050905090810190601f1680156108035780820380516001836020036101000a031916815260200191505b50995050505050505050505060405180910390f35b34801561082457600080fd5b50610889600480360381019080803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929080359060200190929190505050612257565b6040518082815260200191505060405180910390f35b600080600080600360006108b28a61212f565b6000191660001916815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169350600360006108f88961212f565b6000191660001916815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1692506003600061093e8861212f565b6000191660001916815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16915083905060011515600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060070160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060020160149054906101000a900460ff161515148015610ab0575060011515600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060070160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060020160149054906101000a900460ff161515145b1561129b5784600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060070160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000015412158015610bc9575084600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060070160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000015412155b1561129a5784600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060070160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000016000828254039250508190555084600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060070160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600001600082825403925050819055506000600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060070160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600001541415610e15576000600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060070160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060020160146101000a81548160ff0219169083151502179055505b6000600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060070160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600001541415610f3c576000600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060070160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060020160146101000a81548160ff0219169083151502179055505b84600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206004016000828254039250508190555084600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206005016000828254039250508190555060011515600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060070160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060020160149054906101000a900460ff161515141561110d5784600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060070160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160008282540192505081905550611299565b6080604051908101604052808681526020018473ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff16815260200160011515815250600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060070160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000820151816000015560208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060408201518160020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060608201518160020160146101000a81548160ff0219169083151502179055509050505b5b5b5050505050505050565b6000806000600360006112b78761212f565b6000191660001916815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169150600360006112fd8661212f565b6000191660001916815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060070160008273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600001549250505092915050565b600080600360006113d38561212f565b6000191660001916815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050600160008273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060030154915050919050565b6002602052806000526040600020600091509050806000015490806001018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156115095780601f106114de57610100808354040283529160200191611509565b820191906000526020600020905b8154815290600101906020018083116114ec57829003601f168201915b5050505050908060020160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060030154908060040154908060050154908060060160009054906101000a900460ff16908060060160019054906101000a900460ff16905088565b60036020528060005260406000206000915054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000600360006115b38561212f565b6000191660001916815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690506001600054016000819055506101006040519081016040528060005481526020018481526020018273ffffffffffffffffffffffffffffffffffffffff16815260200161c3508152602001600081526020016000815260200183600381111561165157fe5b600381111561165c57fe5b81526020016001151581525060026000805481526020019081526020016000206000820151816000015560208201518160010190805190602001906116a29291906125bd565b5060408201518160020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550606082015181600301556080820151816004015560a0820151816005015560c08201518160060160006101000a81548160ff0219169083600381111561172c57fe5b021790555060e08201518160060160016101000a81548160ff0219169083151502179055509050506002600080548152602001908152602001600020600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008201548160000155600182018160010190805460018160011615610100020316600290046117d892919061263d565b506002820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168160020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506003820154816003015560048201548160040155600582015481600501556006820160009054906101000a900460ff168160060160006101000a81548160ff0219169083600381111561188f57fe5b02179055506006820160019054906101000a900460ff168160060160016101000a81548160ff021916908315150217905550905050505050565b600080600360006118d98761212f565b6000191660001916815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1691506003600061191f8661212f565b6000191660001916815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905060011515600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060070160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060020160149054906101000a900460ff1615151415611a895782600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060070160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160008282540192505081905550611c15565b6080604051908101604052808481526020018273ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff16815260200160011515815250600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060070160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000820151816000015560208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060408201518160020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060608201518160020160146101000a81548160ff0219169083151502179055509050505b82600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206004016000828254019250508190555082600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600501600082825401925050819055505050505050565b60008060008060036000611ccf8861212f565b6000191660001916815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16925060036000611d158761212f565b6000191660001916815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16915060001515600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060070160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060020160149054906101000a900460ff1615151480611eb35750600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060070160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000154600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060030154125b15611ec15760009350612126565b600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060070160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000015490506000600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060070160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060020160146101000a81548160ff02191690831515021790555080600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206003016000828254039250508190555080600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206003016000828254019250508190555080600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206004016000828254039250508190555080600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060050160008282540392505081905550600193505b50505092915050565b600060208201519050919050565b6001602052806000526040600020600091509050806000015490806001018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156121ef5780601f106121c4576101008083540402835291602001916121ef565b820191906000526020600020905b8154815290600101906020018083116121d257829003601f168201915b5050505050908060020160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060030154908060040154908060050154908060060160009054906101000a900460ff16908060060160019054906101000a900460ff16905088565b600080600360006122678661212f565b6000191660001916815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905082600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600501541280612306575060001515600860060160019054906101000a900460ff161515145b80612315575082600860030154125b1561232357600091506125b6565b60011515600860070160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060020160149054906101000a900460ff16151514156123da5782600860070160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000016000828254019250508190555061254e565b608060405190810160405280848152602001600860020160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018273ffffffffffffffffffffffffffffffffffffffff16815260200160011515815250600860070160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000820151816000015560208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060408201518160020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060608201518160020160146101000a81548160ff0219169083151502179055509050505b82600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206004016000828254019250508190555082600860050160008282540192505081905550600191505b5092915050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106125fe57805160ff191683800117855561262c565b8280016001018555821561262c579182015b8281111561262b578251825591602001919060010190612610565b5b50905061263991906126c4565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061267657805485556126b3565b828001600101855582156126b357600052602060002091601f016020900482015b828111156126b2578254825591600101919060010190612697565b5b5090506126c091906126c4565b5090565b6126e691905b808211156126e25760008160009055506001016126ca565b5090565b905600a165627a7a72305820ee32873e75458c01694238212c3ec5c9eb959a2f4307c27590d195d9bffc2d640029";

    public static final String ABI = "[{\"constant\":false,\"inputs\":[{\"name\":\"company_name\",\"type\":\"string\"},{\"name\":\"from_temp_name\",\"type\":\"string\"},{\"name\":\"to_temp_name\",\"type\":\"string\"},{\"name\":\"amount\",\"type\":\"int256\"}],\"name\":\"transferReceipt\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"company_name\",\"type\":\"string\"},{\"name\":\"to_company_name\",\"type\":\"string\"}],\"name\":\"getReceiptAmount\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"company_name\",\"type\":\"string\"}],\"name\":\"getCompanyAsset\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"name\":\"idToCompany\",\"outputs\":[{\"name\":\"company_id\",\"type\":\"int256\"},{\"name\":\"company_name\",\"type\":\"string\"},{\"name\":\"company_address\",\"type\":\"address\"},{\"name\":\"company_asset\",\"type\":\"int256\"},{\"name\":\"company_receipts_in\",\"type\":\"int256\"},{\"name\":\"company_receipts_out\",\"type\":\"int256\"},{\"name\":\"company_type\",\"type\":\"uint8\"},{\"name\":\"valid\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"bytes32\"}],\"name\":\"stringToCompanyAddress\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"company_name\",\"type\":\"string\"},{\"name\":\"company_type\",\"type\":\"int256\"}],\"name\":\"registerCompany\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"company_name\",\"type\":\"string\"},{\"name\":\"from_temp_name\",\"type\":\"string\"},{\"name\":\"amount\",\"type\":\"int256\"}],\"name\":\"signReceipt\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"company_name\",\"type\":\"string\"},{\"name\":\"from_temp_name\",\"type\":\"string\"}],\"name\":\"clearReceipt\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"source\",\"type\":\"string\"}],\"name\":\"stringToBytes32\",\"outputs\":[{\"name\":\"result\",\"type\":\"bytes32\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"address\"}],\"name\":\"addressToCompany\",\"outputs\":[{\"name\":\"company_id\",\"type\":\"int256\"},{\"name\":\"company_name\",\"type\":\"string\"},{\"name\":\"company_address\",\"type\":\"address\"},{\"name\":\"company_asset\",\"type\":\"int256\"},{\"name\":\"company_receipts_in\",\"type\":\"int256\"},{\"name\":\"company_receipts_out\",\"type\":\"int256\"},{\"name\":\"company_type\",\"type\":\"uint8\"},{\"name\":\"valid\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"company_name\",\"type\":\"string\"},{\"name\":\"amount\",\"type\":\"int256\"}],\"name\":\"financingWithReceipt\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"}]";

    public static final String FUNC_TRANSFERRECEIPT = "transferReceipt";

    public static final String FUNC_GETRECEIPTAMOUNT = "getReceiptAmount";

    public static final String FUNC_GETCOMPANYASSET = "getCompanyAsset";

    public static final String FUNC_IDTOCOMPANY = "idToCompany";

    public static final String FUNC_STRINGTOCOMPANYADDRESS = "stringToCompanyAddress";

    public static final String FUNC_REGISTERCOMPANY = "registerCompany";

    public static final String FUNC_SIGNRECEIPT = "signReceipt";

    public static final String FUNC_CLEARRECEIPT = "clearReceipt";

    public static final String FUNC_STRINGTOBYTES32 = "stringToBytes32";

    public static final String FUNC_ADDRESSTOCOMPANY = "addressToCompany";

    public static final String FUNC_FINANCINGWITHRECEIPT = "financingWithReceipt";

    @Deprecated
    protected VehicleSupplyChain(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected VehicleSupplyChain(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected VehicleSupplyChain(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected VehicleSupplyChain(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> transferReceipt(String company_name, String from_temp_name, String to_temp_name, BigInteger amount) {
        final Function function = new Function(
                FUNC_TRANSFERRECEIPT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(company_name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(from_temp_name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(to_temp_name), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void transferReceipt(String company_name, String from_temp_name, String to_temp_name, BigInteger amount, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_TRANSFERRECEIPT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(company_name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(from_temp_name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(to_temp_name), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String transferReceiptSeq(String company_name, String from_temp_name, String to_temp_name, BigInteger amount) {
        final Function function = new Function(
                FUNC_TRANSFERRECEIPT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(company_name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(from_temp_name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(to_temp_name), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> getReceiptAmount(String company_name, String to_company_name) {
        final Function function = new Function(
                FUNC_GETRECEIPTAMOUNT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(company_name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(to_company_name)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void getReceiptAmount(String company_name, String to_company_name, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_GETRECEIPTAMOUNT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(company_name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(to_company_name)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getReceiptAmountSeq(String company_name, String to_company_name) {
        final Function function = new Function(
                FUNC_GETRECEIPTAMOUNT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(company_name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(to_company_name)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> getCompanyAsset(String company_name) {
        final Function function = new Function(
                FUNC_GETCOMPANYASSET, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(company_name)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void getCompanyAsset(String company_name, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_GETCOMPANYASSET, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(company_name)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getCompanyAssetSeq(String company_name) {
        final Function function = new Function(
                FUNC_GETCOMPANYASSET, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(company_name)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<Tuple8<BigInteger, String, String, BigInteger, BigInteger, BigInteger, BigInteger, Boolean>> idToCompany(BigInteger param0) {
        final Function function = new Function(FUNC_IDTOCOMPANY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Int256>() {}, new TypeReference<Int256>() {}, new TypeReference<Int256>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bool>() {}));
        return new RemoteCall<Tuple8<BigInteger, String, String, BigInteger, BigInteger, BigInteger, BigInteger, Boolean>>(
                new Callable<Tuple8<BigInteger, String, String, BigInteger, BigInteger, BigInteger, BigInteger, Boolean>>() {
                    @Override
                    public Tuple8<BigInteger, String, String, BigInteger, BigInteger, BigInteger, BigInteger, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple8<BigInteger, String, String, BigInteger, BigInteger, BigInteger, BigInteger, Boolean>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue(), 
                                (Boolean) results.get(7).getValue());
                    }
                });
    }

    public RemoteCall<String> stringToCompanyAddress(byte[] param0) {
        final Function function = new Function(FUNC_STRINGTOCOMPANYADDRESS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> registerCompany(String company_name, BigInteger company_type) {
        final Function function = new Function(
                FUNC_REGISTERCOMPANY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(company_name), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(company_type)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void registerCompany(String company_name, BigInteger company_type, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_REGISTERCOMPANY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(company_name), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(company_type)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String registerCompanySeq(String company_name, BigInteger company_type) {
        final Function function = new Function(
                FUNC_REGISTERCOMPANY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(company_name), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(company_type)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> signReceipt(String company_name, String from_temp_name, BigInteger amount) {
        final Function function = new Function(
                FUNC_SIGNRECEIPT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(company_name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(from_temp_name), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void signReceipt(String company_name, String from_temp_name, BigInteger amount, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SIGNRECEIPT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(company_name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(from_temp_name), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String signReceiptSeq(String company_name, String from_temp_name, BigInteger amount) {
        final Function function = new Function(
                FUNC_SIGNRECEIPT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(company_name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(from_temp_name), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> clearReceipt(String company_name, String from_temp_name) {
        final Function function = new Function(
                FUNC_CLEARRECEIPT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(company_name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(from_temp_name)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void clearReceipt(String company_name, String from_temp_name, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_CLEARRECEIPT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(company_name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(from_temp_name)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String clearReceiptSeq(String company_name, String from_temp_name) {
        final Function function = new Function(
                FUNC_CLEARRECEIPT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(company_name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(from_temp_name)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> stringToBytes32(String source) {
        final Function function = new Function(
                FUNC_STRINGTOBYTES32, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(source)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void stringToBytes32(String source, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_STRINGTOBYTES32, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(source)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String stringToBytes32Seq(String source) {
        final Function function = new Function(
                FUNC_STRINGTOBYTES32, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(source)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<Tuple8<BigInteger, String, String, BigInteger, BigInteger, BigInteger, BigInteger, Boolean>> addressToCompany(String param0) {
        final Function function = new Function(FUNC_ADDRESSTOCOMPANY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Int256>() {}, new TypeReference<Int256>() {}, new TypeReference<Int256>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bool>() {}));
        return new RemoteCall<Tuple8<BigInteger, String, String, BigInteger, BigInteger, BigInteger, BigInteger, Boolean>>(
                new Callable<Tuple8<BigInteger, String, String, BigInteger, BigInteger, BigInteger, BigInteger, Boolean>>() {
                    @Override
                    public Tuple8<BigInteger, String, String, BigInteger, BigInteger, BigInteger, BigInteger, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple8<BigInteger, String, String, BigInteger, BigInteger, BigInteger, BigInteger, Boolean>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue(), 
                                (Boolean) results.get(7).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> financingWithReceipt(String company_name, BigInteger amount) {
        final Function function = new Function(
                FUNC_FINANCINGWITHRECEIPT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(company_name), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void financingWithReceipt(String company_name, BigInteger amount, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_FINANCINGWITHRECEIPT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(company_name), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String financingWithReceiptSeq(String company_name, BigInteger amount) {
        final Function function = new Function(
                FUNC_FINANCINGWITHRECEIPT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(company_name), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    @Deprecated
    public static VehicleSupplyChain load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new VehicleSupplyChain(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static VehicleSupplyChain load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new VehicleSupplyChain(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static VehicleSupplyChain load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new VehicleSupplyChain(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static VehicleSupplyChain load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new VehicleSupplyChain(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<VehicleSupplyChain> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(VehicleSupplyChain.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<VehicleSupplyChain> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(VehicleSupplyChain.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<VehicleSupplyChain> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(VehicleSupplyChain.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<VehicleSupplyChain> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(VehicleSupplyChain.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
