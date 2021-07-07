package com.springbatch.arquivomultiplosformatos.reader;

import java.util.Objects;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;

import com.springbatch.arquivomultiplosformatos.dominio.Cliente;
import com.springbatch.arquivomultiplosformatos.dominio.Transacao;

public class ArquivoClienteTransacaoReader implements ItemStreamReader<Cliente> {
	
	private Object objAtual;
	private ItemStreamReader<Object> delegate;
	
	

	public ArquivoClienteTransacaoReader(ItemStreamReader<Object> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		delegate.open(executionContext);
		
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		delegate.update(executionContext);
		
	}

	@Override
	public void close() throws ItemStreamException {
		delegate.close();
		
	}

	@Override
	public Cliente read() throws Exception {
		// TODO Auto-generated method stub
		
		if(Objects.isNull(objAtual)) {
			
				objAtual = delegate.read();
			
		}
				
		Cliente c = (Cliente) objAtual;
		objAtual = null;
		if(!Objects.isNull(c)) {
			while(peek() instanceof Transacao) {
				c.getTransacoes().add((Transacao) objAtual );
			}
		}
		
		return c;
	}

	private Object peek() throws  Exception {
		// TODO Auto-generated method stub
		objAtual = delegate.read();
		return objAtual;
	}

}
