import React from 'react';
import { Form, Input, Button } from 'antd';
import { Layout, H1, Content } from '@src/components/common/styles';

const layout = {
  labelCol: { span: 3 },
  wrapperCol: { span: 10 },
};
const tailLayout = {
  wrapperCol: { offset: 6, span: 12 },
};

const SignUp: React.FC = () => {
  const submitHandler = React.useCallback((form) => {
    delete form.memberRePassword;
    
    fetch('/api/member/register', {
      method: 'post',
      headers: {
        "Content-Type": "application/json;charset=UTF-8"
      },
      body: JSON.stringify(form)
    });
  }, []);

  return (
    <Layout>
      <H1>Sign Up</H1>
      <Content>
        <Form
            {...layout}
            initialValues={{memberId: '', memberPassword: ''}}
            onFinish={submitHandler}
          >
            <Form.Item
              label="아이디"
              name="memberId"
              rules={[{ required: true, message: '아이디를 입력해주세요.' }]}
            >
              <Input />
            </Form.Item>
            <Form.Item
              label="비밀번호"
              name="memberPassword"
              rules={[{required: true, message: '비밀번호를 입력해주세요.' }]}
            >
              <Input.Password />
            </Form.Item>
            <Form.Item
              label="비밀번호 재입력"
              name="memberRePassword"
              rules={[
                {required: true, message: '비밀번호를 입력해주세요.' },
                ({getFieldValue}) => ({
                  validator(_, value) {
                    if(getFieldValue('memberPassword') !== value) {
                      return Promise.reject('비밀번호와 동일하게 입력해주세요.');
                    } 
                    return Promise.resolve('비밀번호와 동일합니다.');
                  } 
                })
              ]}
            >
              <Input.Password />
            </Form.Item>
            <Form.Item
              label="닉네임"
              name="memberNickname"
            >
              <Input />
            </Form.Item>
            <Form.Item {...tailLayout}>
              <Button type="primary" htmlType="submit">
                회원가입
              </Button>
            </Form.Item>
          </Form>
      </Content>
    </Layout>
  )
};

SignUp.displayName = 'SignUp';
export default SignUp;