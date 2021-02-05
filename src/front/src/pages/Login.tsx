import React from 'react';
import styled from '@emotion/styled';
import { Form, Input, Button, Checkbox } from 'antd';

const layout = {
  labelCol: { span: 2 },
  wrapperCol: { span: 10 },
};
const tailLayout = {
  wrapperCol: { offset: 6, span: 12 },
};


const Login: React.FC = () => {
  const submitHandler = React.useCallback((form) => {
    
  }, []);
  return (
    <Wrapper>
      <H1>Login</H1>
      <Content>
        <Form
          {...layout}
          initialValues={{username: '', password: ''}}
          onFinish={submitHandler}
        >
          <Form.Item
            label="아이디"
            name="username"
            rules={[{ required: true, message: '아이디를 입력해주세요.' }]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            label="비밀번호"
            name="password"
            rules={[{ required: true, message: '비밀번호를 입력해주세요.' }]}
          >
            <Input.Password />
          </Form.Item>
          <Form.Item {...tailLayout}>
            <Button type="primary" htmlType="submit">
              로그인
            </Button>
          </Form.Item>
        </Form>
      </Content>
    </Wrapper>
  )
}


const H1 = styled.div`
  padding: 10px;
  font-size: 28px;
  border-bottom: 1px solid #333;
`;

const Content = styled.div`
  margin-top: 20px;  
`;

const Wrapper = styled.div`
  width: 1200px;
  margin: 30px auto;
`;

Login.displayName = 'Login';
export default Login;